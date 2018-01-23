--Created by Vizeng Lee
--This script selects all presidental votes and shows the total votes and total presidental votes
-- based on the counties
--All of these adds up to total cotes

select countyname,pctname,totvoting,usprstotal,
 usprsr, USPRSDFL,usprscp,
USPRSLMN,USPRSSWP,USPRSGP,USPRSADP, USPRSIP,
USPRSLIB, USPRSWI from results where countyname = "Aitkin"