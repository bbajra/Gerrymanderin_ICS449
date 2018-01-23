--Created by Vizeng Lee
--Selects all the types of votes based on precint
select countyname,pctname,TOTVOTING, SIGNATURES,AB_MB,
FEDONLYAB,PRESONLYAB  from results