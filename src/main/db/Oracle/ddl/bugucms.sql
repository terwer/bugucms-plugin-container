--创建临时表空间
CREATE TEMPORARY TABLESPACE BGDATA_TEMP TEMPFILE '/data/app/oracle/oradata/orcl/temp02.dbf ' SIZE 512M REUSE AUTOEXTEND ON NEXT 50M MAXSIZE UNLIMITED;

--创建数据表空间
CREATE TABLESPACE BGDATA DATAFILE '/data/app/oracle/oradata/orcl/bg_data.dbf' SIZE 50M REUSE AUTOEXTEND ON NEXT 50M MAXSIZE UNLIMITED;

--删除表空间
--DROP TABLESPACE BGDATA INCLUDING CONTENTS AND DATAFILES;

-- Create the user
create user bg
  identified by cms2018
  default tablespace BGDATA
  temporary tablespace BGDATA_TEMP;
-- Grant/Revoke role privileges
grant connect to bg;
grant resource to bg;
grant dba to bg;