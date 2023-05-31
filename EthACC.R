
########################################################## note: require rrBLUP, glmnet, parcor, EN.FDR.r and mlmm.gwas
# Function that computes the Estimated THeoritical ACCuracy (EthAcc)# on the basis of the formula of Rabier et al. PlosOne, 2016
# Causal SNPs can be located by several gwas methods or given by the user
#Entry
#-----#x_train is the SNP dose matrix for the training population. It is a n by m matrix,
#               where n=number of  individuals, m=number of SNPs,
#               with rownames(x_train)=individual names, and colnames(x_train)=SNP names.
#x_test is the same codage matrix as x_train but for individuals in the test population
#y_train is the phenotype of individual in the training population.
#               It is a vector of length n=number of  individuals,
#               with names(y_train)=individual names
#snp.pop_train is the name of the SNPs=QTLs in the causal model if known,
#               must be included in colnames(x_train)
#meth is the method to find causal SNPs, can be "MLMM", "EN05.FDR", "adpLASSO"
#               or a triple for penalized method with
#         alpha value, "min" or "1se", TRUE or FALSE for SNP standardization#examples:
#res.EthAcc<-compute.EthAcc(x.train,x.test,y.train,snp.pop_train=colnames(x.train)[1:10])
#res.EthAcc<-compute.EthAcc(x.train,x.test,y.train,meth="MLMM")
#res.EthAcc<-compute.EthAcc(x.train,x.test,y.train,meth=c(0.5,"1se",TRUE) )
##WARNING: too small MAFs in x.train give innacurate results
################################auxiliary functions
library(rrBLUP, quietly = TRUE)  #dependency on rrBLUP package
library(mlmm.gwas, quietly = TRUE) #de\E2\F4\E5\F4pendency on mlmm.gwas package
library(glmnet, quietly = TRUE) #dependency on glmnet
############################################
# function to compute VanRanden type kinship
kinship<-function(x){
  x.center<-scale(x,center=TRUE,scale=FALSE)
  KK<-x.center%*%t(x.center)
  cst.VR<-sum(apply(x.center,2,var))
  KK<-KK/cst.VR
  KK
  return(list(KK=KK, cst.VR=cst.VR)) #kinship and VanRandem constant
}
#function to find causal QTL
gwas.togetcausalSNP<-function(x_train,y_train,meth){
  snp.pop_train<-NULL
  x_train.c<-scale(x_train,center=TRUE,scale=FALSE)
  nstep<-length(y_train) -10  #10 to keep degrees of freedom for the residual
  if(length(meth)==1){
    if(meth=="MLMM"){
      kk.pop_train<-kinship(x_train)$KK  #kinship for GWAS
      gwas.pop_train<-mlmm_allmodels(y_train,list(x_train),list(kk.pop_train),2,nstep)
      snp.pop_train<-NomSNP(gwas.pop_train) #estimated causal SNP
    }
    if(meth=="adpLASSO"){ 
      #adaptive LASSO
      x_train.cr<-scale(x_train,center=TRUE,scale=TRUE)
      y_train.cr<-scale(y_train,center=TRUE,scale=TRUE)
      res.adpLASSO<-adalasso(x_train.cr, y_train.cr,k=5)
      snp.pop_train<-colnames(x_train)[res.adpLASSO$coefficients.adalasso!=0]}
  }
  if(length(meth)==3){
    if(meth[3]==TRUE) {  
      #penalized regression
      res.lasso<-glmnet(x_train.c,y_train,family="gaussian",standardize=TRUE,alpha=as.numeric(meth[1]))
      res.cv.lasso<-cv.glmnet(x_train.c,y_train,family="gaussian",standardize=TRUE,alpha=as.numeric(meth[1]))
    }
    if(meth[3]==FALSE) {
      res.lasso<-glmnet(x_train.c,y_train,family="gaussian",standardize=FALSE,alpha=as.numeric(meth[1]))
      res.cv.lasso<-cv.glmnet(x_train.c,y_train,family="gaussian",standardize=FALSE,alpha=as.numeric(meth[1]))
    }
    if(meth[2]=="min"){
      temp<-abs(res.lasso$lambda-res.cv.lasso$lambda.min)
      id.lambda<-which(temp==min(temp))
      snp.pop_train<-names(which(res.lasso$beta[,id.lambda]!=0))
    }
    if(meth[2]=="1se"){
      temp<-abs(res.lasso$lambda-res.cv.lasso$lambda.1se)
      id.lambda<-which(temp==min(temp))
      snp.pop_train<-names(which(res.lasso$beta[,id.lambda]!=0))
    }
  }
  snp.pop_train
}
##################333
# function to get associated SNP in mlmm results
NomSNP<-function(res.mlmm){
  names.snp<-NULLlast.snp<-NULLn.step<-length(res.mlmm)
  if(n.step>2) {
    id<-grep("selec_",names(res.mlmm[[n.step]]) )
    names.snp<-names(res.mlmm[[n.step]])[id]
    names.snp<-unlist(sapply(names.snp,function(x){
      unlist(strsplit(x,"selec_"))[2]
      }))
    #add the last associated SNP
    id<-which( res.mlmm[[n.step]][-(1:(n.step-2))]==min( res.mlmm[[n.step]][-(1:(n.step-2))],na.rm=TRUE ) )
    last.snp<-names( res.mlmm[[n.step]])[-(1:(n.step-2))][id]
  }
  if(n.step==2) {
    id<-which(res.mlmm[[n.step]]==min( res.mlmm[[n.step]] ,na.rm=TRUE) )
    last.snp<-names( res.mlmm[[n.step]])[id]
  }
  names.snp<-c(names.snp,last.snp)
}
# function to compute theoretical accuracy
Theo.acc<-function(Mtest,Mtrain,effect,Hinv,Ve){
  #theoritical formula of Rabier et al. PlosOne, 2016, adapted to non centered genotypic matrices
  if( is.null(effect) ) return(NA)
  ##sort  on names
  if( length(effect) > 1 ) { 
    effect<-effect[sort(names(effect))] 
    }
  Mtrain <- Mtrain[,sort(colnames(Mtrain))]
  Mtest <- Mtest[,sort(colnames(Mtest))]
  if( length(effect) > 1 ) {
    #predictor for training individuals
    predtrain <- as.matrix(Mtrain[,which(colnames(Mtrain)%in%names(effect))]) %*% as.matrix(effect)
    #predictor for test individuals
    predtest <- as.matrix(Mtest[,which(colnames(Mtest)%in%names(effect))]) %*% as.matrix(effect)
  } else {
      #predictor for training individuals
    predtrain <- as.matrix( Mtrain[,which(colnames(Mtrain)%in%names(effect))] * effect )
    #predictor for test individuals
    predtest <- as.matrix( Mtest[,which(colnames(Mtest)%in%names(effect))] * effect )
  }
  Mtest<-scale(Mtest,center=TRUE,scale=FALSE)  
  #case of non centered Mtest
  mu.Hinv<- as.matrix(apply(Hinv,1,sum)) 
  #case of non centered Mtrain
  Hinv.cor<- Hinv -  ( mu.Hinv %*% t(mu.Hinv)) / sum(Hinv) 
  #case of non centered Mtrain
  espfortrain <- t(Mtrain) %*% Hinv.cor %*% predtrain
  nume <- t(predtest) %*% Mtest %*% espfortrain / nrow(Mtest)
  RROracle <- Mtest %*% t(Mtrain) %*% Hinv.cor
  termDesign <- Ve * sum(RROracle^2)/ nrow(Mtest)
  termvar <- t(espfortrain) %*%  t(Mtest) %*% Mtest  %*% espfortrain /nrow(Mtest)
  Vg <- var( predtest) 
  #genetic variance in the causal model estimated on test individuals
  if( Vg >1) message("WARNING: estimated genetic variance too great, result innacurate")
  res <- nume / sqrt( ( termDesign + termvar) * (Vg + Ve) )
  return(res)
}
#################principal function
compute.EthAcc<-function(x_train,x_test,y_train,snp.pop_train=NULL,meth=NULL){
  #controls
  if(is.null(snp.pop_train) & is.null(meth) ) stop
  stopifnot( ncol(x_train)==ncol(x_test) )
  stopifnot( length(y_train)==nrow(x_train) )
  x_train<-x_train[,sort(colnames(x_train))]
  x_test<-x_test[,sort(colnames(x_test))]
  stopifnot( sum( colnames(x_train)!=colnames(x_test) )==0 )
  if(!is.null(snp.pop_train)) stopifnot( length( which( colnames(x_train)%in%snp.pop_train) ) ==length(snp.pop_train) )
  x_train<-x_train[sort(rownames(x_train)),]
  #y_train<-y_train[sort(colnames(y_train))]
  stopifnot( sum( rownames(x_train)!=names(y_train) )==0 )
  #estimate causal location by gwas
  if(is.null(snp.pop_train)) snp.pop_train<-gwas.togetcausalSNP(x_train,y_train,meth)
  #estimation
  y_train<-y_train/sd(y_train)  #standardization of phenotype
  #get Hinv in rrBLUP model
  rrblup.pop_train<-mixed.solve(y_train,X = rep(1,length(y_train)),Z=x_train,K=NULL,SE=FALSE,return.Hinv=TRUE)
  x.snp.pop_train<-as.matrix(x_train[, snp.pop_train ])
  if(length(snp.pop_train)<(length(y_train)-1) & length(snp.pop_train)!=0){
    lm.in.causal<-lm(t(y_train)~1+x.snp.pop_train) 
    #causal model
    eff.snp.pop_train<-lm.in.causal$coefficients[-1] 
    #causal SNP=QTL effect estimation
    names(eff.snp.pop_train)<-snp.pop_train
    eff.snp.pop_train<-eff.snp.pop_train[!is.na(eff.snp.pop_train)]
    #residual variance estimation in the causal model
    ve.pop_train<-summary(lm.in.causal)$sigma^2
    res<-Theo.acc(x_test,x_train,eff.snp.pop_train,rrblup.pop_train$Hinv,ve.pop_train)
#    print(res)
  } else {
      res<-1e-19
  }
  names(res)<-paste("EthAcc", do.call(paste, c(as.list(meth), sep="_")),sep="_" )
  return(res)
}
###

  
  
  
  
  
  
  
  
  
  
  
  
  
  
