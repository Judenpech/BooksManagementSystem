USE [BMSBase] 

--插入用户信息表
INSERT UserInformation						
	(Id
	,Name
	,Sex
	,Age
	,Identitycard
	,Workdate
	,Tel
	,Yajin
	,Password
	,admin)
	VALUES					
	(1,'唐紫麒','女',18 ,'352188200003221234','2017-11-11','18396129073',10,'8888','张敏')
	,(2,'张振涛','男',18 ,'352188199907121234','2017-11-11','13750861454',10,'8888','施罗霞')
	,(3,'何倩','女',18, '352188199911301234','2017-11-11','18259068070',10,'8888','吴小菁')
	,(4,'兰碧玲','女',18 ,	'352188199803021234','2017-11-11','18396128732',10,'8888','李靖')
	,(5	,'章小芬','女',18, '352188199701221234','2017-11-11',	'18396129575',10,'8888','朱述欣')
	,(6	,'雷燕'	,'女',18,'352188198804011234',	'2017-11-11',	'13635202938',10,'8888','刘昭凤')
	,(7	,'赵琳'	,'女',23,'352188199602021234','2012-11-11','13635202999',10,'8888','张敏')
	,(8	,'夏季'	,'女',41, '352188197809091234','2012-11-11','13645202938',10,'8888','施罗霞')
	,(9	,'龚俊'	,'男',37,'352188198002012123','2012-11-11','15235202938',10,'8888','吴小菁')


--插入图书类别表
INSERT BookType
	(TypeName
	,Id)
	VALUES
	('中国文学',1)
	,('医药卫生',2)
	,('小说类',3)
	,('计算机技术',4)
	,('外国文学',5)
	,('军事',6)

--插入读者信息表
INSERT ReaderInformation
   (Name
	,Sex
	,Age
	,identityCard
	,Date
	,maxNum
	,Tel
	,keepMoney
	,Zj
	,Zy
	,ISBN
	,bztime)
	VALUES
	('唐紫麒','女',	18 	,'352188200003221234','2017-3-11' ,6,'13124567890',10	,'身份证','学生','9787513506915','2017-9-20')
	,('张振涛','男',18 ,'352188199907121234','2017-3-12' ,6,'13243564343',10,'身份证','学生','9787811373226','2017-9-20')
	,('何倩','女',18,'352188199911301234','2017-9-11' ,6 ,'13243435656',10,'身份证','学生','9787562421467','2017-9-20')
	,('兰碧玲','女',18,'352188199803021234','2017-11-11',6,'13467675432',10,'身份证','学生','9787811050387','2016-9-20')
	,('章小芬','女',18,'352188199701221234','2017-5-21',6 ,'13454342789',10,'身份证','学生','9787544607513','2015-9-20')
	,('雷燕','女',18,'352188198804011234','2017-12-11',6 ,'13265768767',10,'身份证','学生','9787513536509','2015-9-20')
	,('赵琳','女',23,'352188199602021234','2017-6-11',6,'13134534907',10,'身份证','学生','9787544628365','2014-9-20')
	,('夏季','女',41,'352188197809091234','2017-9-27' ,8 ,'15324563456',15,'身份证','老师','9787301204580','2010-10-21')
	,('龚俊','男',37,'352188198002012123','2017-11-11',8 ,'13244565678',15,'身份证','老师','9787562154259','2005-5-23')

--插入图书信息表
INSERT BookInformation
	(ISBN
	,typeId
	,Bookname
	,Writer
	,Translater
	,Publisher
	,Date
	,Price)
	VALUES
	('9787513506915',1,'翻译美学理论','刘宓庆',NULL,'外语教学与研究出版社','2011-3-1',49.90)
	,('9787811373226',2,'医学英语入门','姜瑾',NULL,'苏州大学出版社','2009-11-1',38.00)
	,('9787562421467',2,'医学英语翻译与写作教程','王燕',NULL,'重庆大学出版社','2008-9-1',27.00)
	,('9787811050387',2,'医学英语读写译教程','贾德江、刘明东',NULL,'中南大学出版社','2005-2-1',24.00)
	,('9787544607513',1,'翻译概论','姜倩、何刚强',NULL,'上海外语教育出版社','2008-9-1',28.00)
	,('9787513536509',1,'简明中西翻译史','谢天振、何绍斌',NULL,'外语教学与研究出版社','2013-11-1',29.80)
	,('9787544628365',1,'“传神达意”翻译理论研究','门顺德',NULL,'上海外语教育出版社','2012-12-1',38.00)
	,('9787301204580',1,'《红楼梦》英译艺术比较研究——基于霍克思和杨宪益译本','党争胜',NULL,'北京大学出版社','2012-3-1',42.00)
	,('9787562154259',1,'文化视域及翻译策略：《红楼梦》译本的多维研究','邱进、周洪亮',NULL,'西南师范大学出版社','2011-8-1',48.00)
	,('9787511206961',1,'《红楼梦》中英文语料库的创建及应用研究','刘泽权',NULL,'光明日报出版社','2010-5-1',35.00)
	,('9787544600613',1,'红译艺坛——《红楼梦》翻译艺术研究','冯庆华',NULL,'上海外语教育出版社','2006-9-1',26.00)
	,('9787118076554',1,'英语科技文体：范式与翻译','方梦之',NULL,'国防工业出版社','2011-10-1',32.00)
	,('9787544606479',1,'英汉-汉英应用翻译综合教程','方梦之、毛忠明',NULL,'上海外语教育出版社','2008-6-1',33.00)
	,('9787307080515',1,'翻译批评与赏析（第二版）','李明',NULL,'武汉大学出版社','2010-8-1',34.00)
	,('9787544617277',1,'翻译批评模式研究','肖维青',NULL,'上海外语教育出版社','2010-4-1',28.00)
	,('9787544617956',5,'高级译学原典读本','哈迪姆、蒙代',NULL,'上海外语教育出版社','2010-6-1',35.00)
	,('9787810950565',5,'系统中的翻译——描写和系统理论解说','赫曼斯(Hermans,T.)',NULL,'上海外语教育出版社','2004-4-1',12.00)
	,('9787544617765',5,'翻译研究（第三版）','巴斯内特',NULL,'上海外语教育出版社','2010-6-1',18.00)
	,('9787544627634',5,'翻译能力培养','舍夫纳',NULL,'上海外语教育出版社','2012-6-1',28.00)
	,('9787544619035',5,'翻译研究百科全书（第二版）','贝克、萨尔达尼亚',NULL,'上海外语教育出版社','2010-11-1',60.00)
	,('9787544617383',5,'翻译研究入门：理论与应用','哈迪姆、蒙代',NULL,'上海外语教育出版社','2010-5-1',2.00)
	,('9787810950626',5,'当代翻译理论(第二版修订本)','根茨勒(Gentzler,E.)',NULL,'上海外语教育出版社','2004-4-1',14.00)
	,('9787310028306',1,'当代国外翻译理论导读','谢天振',NULL,'南开大学出版社','2008-5-1',40.00)
	,('9787310020584',1,'红楼译评——《红楼梦》翻译研究论文集','刘士聪',NULL,'南开大学出版社','2004-10-1',35.00)
	,('9787806739266',3,'清朝那些事','朱景晖',NULL,'花山文艺出版社','2007-1-1',63.5)
	,('9787115223678',4,'Java从入门到精通','国家863中部软件孵化器',NULL,'人民邮电出版社','2010-4-1',59)

--插入管理员信息表
INSERT tb_operator
    (Id
	,Name
	,Sex
	,Age
	,Identitycard
	,Tel
	,Password
	,admin)
	VALUES
	(1,'张敏','女',18,'3150707008','18396129517','6666',1)
	,(2,'施罗霞','女',18,'3150707009','18396129035','6666',1)
	,(3,'吴小菁','女',18,'3150707010','18396129280','6666',1)
	,(4,'李靖','女',18,'3150707012','18396128763','6666',1)
	,(5,'朱述欣','女',18,'3150707013','18396129675','6666',1)
	,(6,'刘昭凤','女',18,'3150707017','18396128939','6666',1);