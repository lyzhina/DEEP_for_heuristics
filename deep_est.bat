#!/usr/bin/Rscript
### above=BAT, below=R
setwd("/home/maria_lyzhina/hd")
source("EthACC.R")
args <- commandArgs(trailingOnly = T)
g = "/home/maria_lyzhina/hd/data/Wheat_genotype.csv"
ph = "/home/maria_lyzhina/hd/data/Wheat_phenotype.csv"
p = strtoi(args)
x = read.table(g, sep =',',header = TRUE, row.names=1)
y = read.table(ph, sep =',',header = TRUE,row.names=1)

#rownames(x)=c(x[,1])
n = p[1]
parametrs = p[1: n + 1]
parametrs = parametrs + 1
y = y[parametrs,]
y = t(y)
x_test = x[-parametrs,]
x_train = x[parametrs,]
#colnames(y)=c(rownames(x_train))
xx = colnames(x)[parametrs[1 : (n %/% 2)]]
z=compute.EthAcc(x_train, x_test, y, snp.pop_train=xx)
cat(1 - z, '\n')
