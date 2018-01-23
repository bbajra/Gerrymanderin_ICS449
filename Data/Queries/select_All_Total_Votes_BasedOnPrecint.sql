--Created by Vizeng Lee
--all total votes based on precint
--This script alone will tell us all total votes
select countyname,pctname
,totvoting,usprstotal,USrepTOTAL,
MNSENTOTAL, MNLEGTOTAL
from results where pctname = var