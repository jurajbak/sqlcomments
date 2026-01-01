-- HSQLDB.sql

CREATE TABLE dept (
   DEPTNO integer NOT NULL,
   DNAME varchar(20) NOT NULL,
   LOC varchar(20) NOT NULL,
   
   PRIMARY KEY (DEPTNO)
);

INSERT INTO dept VALUES (10, 'ACCOUNTING', 'NEW YORK');
INSERT INTO dept VALUES (20, 'RESEARCH', 'DALLAS');
INSERT INTO dept VALUES (30, 'SALES', 'CHICAGO');
INSERT INTO dept VALUES (40, 'OPERATIONS', 'BOSTON');


CREATE TABLE emp (
   EMPNO integer NOT NULL,
   ENAME varchar(20) NOT NULL,
   JOB varchar(20) NOT NULL,
   MGR integer,
   HIREDATE date NOT NULL,
   SAL integer NOT NULL,
   COMM integer,
   DEPTNO integer NOT NULL,
   
   PRIMARY KEY (EMPNO),
   CONSTRAINT fk_MGR FOREIGN KEY (MGR) REFERENCES emp (EMPNO)
      ON DELETE SET NULL
      ON UPDATE CASCADE,
   CONSTRAINT fk_DEPTNO FOREIGN KEY (DEPTNO) REFERENCES dept (DEPTNO)
      ON DELETE RESTRICT
	  ON UPDATE NO ACTION
);

INSERT INTO emp VALUES (7839, 'KING', 'PRESIDENT', NULL, '1981-11-17', 5000, NULL, 10);
INSERT INTO emp VALUES (7698, 'BLAKE', 'MANAGER', 7839, '1981-05-01', 2850, NULL, 30);
INSERT INTO emp VALUES (7654, 'MARTIN', 'SALESMAN', 7698, '1981-09-28', 1250, 1400, 30);
INSERT INTO emp VALUES (7499, 'ALLEN', 'SALESMAN', 7698, '1981-02-20', 1600, 300, 30);
INSERT INTO emp VALUES (7521, 'WARD', 'SALESMAN', 7698, '1981-02-22', 1250, 500, 30);
INSERT INTO emp VALUES (7900, 'JAMES', 'CLERK', 7698, '1981-12-03', 950, NULL, 30);
INSERT INTO emp VALUES (7844, 'TURNER', 'SALESMAN', 7698, '1981-09-08', 1500, 0, 30);
INSERT INTO emp VALUES (7782, 'CLARK', 'MANAGER', 7839, '1981-06-09', 2450, NULL, 10);
INSERT INTO emp VALUES (7934, 'MILLER', 'CLERK', 7782, '1982-01-23', 1300, NULL, 10);
INSERT INTO emp VALUES (7566, 'JONES', 'MANAGER', 7839, '1981-04-02', 2975, NULL, 20);
INSERT INTO emp VALUES (7788, 'SCOTT', 'ANALYST', 7566, '1982-12-09', 3000, NULL, 20);
INSERT INTO emp VALUES (7876, 'ADAMS', 'CLERK', 7788, '1983-01-12', 1100, NULL, 20);
INSERT INTO emp VALUES (7902, 'FORD', 'ANALYST', 7566, '1981-12-03', 3000, NULL, 20);
INSERT INTO emp VALUES (7369, 'SMITH', 'CLERK', 7902, '1980-12-17', 800, NULL, 20);


CREATE TABLE proj (
   PROJID integer NOT NULL,
   EMPNO integer NOT NULL,
   STARTDATE date NOT NULL,
   ENDDATE date NOT NULL,
   
   PRIMARY KEY (PROJID),
   CONSTRAINT fk_PROJ FOREIGN KEY (EMPNO) REFERENCES emp (EMPNO)
      ON DELETE NO ACTION
      ON UPDATE CASCADE
);

INSERT INTO proj VALUES (1, 7782, '2005-06-16', '2005-06-18');
INSERT INTO proj VALUES (4, 7782, '2005-06-19', '2005-06-24');
INSERT INTO proj VALUES (7, 7782, '2005-06-22', '2005-06-25');
INSERT INTO proj VALUES (10, 7782, '2005-06-25', '2005-06-28');
INSERT INTO proj VALUES (13, 7782, '2005-06-28', '2005-07-02');
INSERT INTO proj VALUES (2, 7839, '2005-06-17', '2005-06-21');
INSERT INTO proj VALUES (8, 7839, '2005-06-23', '2005-06-25');
INSERT INTO proj VALUES (14, 7839, '2005-06-29', '2005-06-30');
INSERT INTO proj VALUES (11, 7839, '2005-06-26', '2005-06-27');
INSERT INTO proj VALUES (5, 7839, '2005-06-20', '2005-06-24');
INSERT INTO proj VALUES (3, 7934, '2005-06-18', '2005-06-22');
INSERT INTO proj VALUES (12, 7934, '2005-06-27', '2005-06-28');
INSERT INTO proj VALUES (15, 7934, '2005-06-30', '2005-07-03');
INSERT INTO proj VALUES (9, 7934, '2005-06-24', '2005-06-27');
INSERT INTO proj VALUES (6, 7934, '2005-06-21', '2005-06-23');


CREATE VIEW Managers AS
SELECT m.ENAME AS Manager, e.ENAME AS Employee
FROM emp AS e LEFT JOIN emp AS m ON e.MGR = m.EMPNO
ORDER BY m.ENAME, e.ENAME;


CREATE TABLE datatypes (

    -- Integer numeric types
    tinyint_            TINYINT,
    smallint_           SMALLINT,
    integer_            INTEGER,
    bigint_             BIGINT,

    -- Floating-point types
    real_               REAL,
    float_              FLOAT,
    double_             DOUBLE,

    -- Exact numeric types
    numeric_            NUMERIC(10,2),
    decimal_            DECIMAL(10,2),

    -- Boolean
    boolean_            BOOLEAN,

    -- Character types
    char_               CHAR(10),
    varchar_            VARCHAR(255),
    varchar_ignorecase_ VARCHAR_IGNORECASE(255),
    clob_               CLOB,

    -- Binary types
    binary_             BINARY(16),
    varbinary_          VARBINARY(255),
    blob_               BLOB,

    -- Date and time types
    date_               DATE,
    time_               TIME,
    timestamp_          TIMESTAMP,
    timestamp_tz_       TIMESTAMP WITH TIME ZONE,

    -- Interval types
    interval_ym_        INTERVAL YEAR TO MONTH,
    interval_ds_        INTERVAL DAY TO SECOND,

    -- Other supported types
    uuid_               UUID,
    array_              INTEGER ARRAY[10],

   PRIMARY KEY(integer_)
);

INSERT INTO datatypes (
    tinyint_,
    smallint_,
    integer_,
    bigint_,
    real_,
    float_,
    double_,
    numeric_,
    decimal_,
    boolean_,
    char_,
    varchar_,
    varchar_ignorecase_,
    clob_,
    binary_,
    varbinary_,
    blob_,
    date_,
    time_,
    timestamp_,
    timestamp_tz_,
    interval_ym_,
    interval_ds_,
    uuid_,
    array_
) VALUES (
    12,                                   -- TINYINT
    32000,                                -- SMALLINT
    123456,                               -- INTEGER
    9876543210,                           -- BIGINT
    1.23,                                 -- REAL
    4.56,                                 -- FLOAT
    7.89,                                 -- DOUBLE
    12345.67,                             -- NUMERIC(10,2)
    890.12,                               -- DECIMAL(10,2)
    TRUE,                                 -- BOOLEAN
    'CHARVALUE',                          -- CHAR(10)
    'Varchar text',                       -- VARCHAR
    'IgnoreCase Text',                   -- VARCHAR_IGNORECASE
    'This is a CLOB example',             -- CLOB
    X'00112233445566778899AABBCCDDEEFF',  -- BINARY(16)
    X'DEADBEEF',                          -- VARBINARY
    X'CAFEBABE',                          -- BLOB
    DATE '2024-06-01',                    -- DATE
    TIME '13:45:30',                      -- TIME
    TIMESTAMP '2024-06-01 13:45:30',      -- TIMESTAMP
    TIMESTAMP '2024-06-01 13:45:30+02:00',-- TIMESTAMP WITH TIME ZONE
    INTERVAL '1-2' YEAR TO MONTH,         -- INTERVAL YEAR TO MONTH
    INTERVAL '3 04:05:06' DAY TO SECOND,  -- INTERVAL DAY TO SECOND
    '550e8400-e29b-41d4-a716-446655440000',-- UUID
    ARRAY[1, 2, 3, 4, 5]                  -- INTEGER ARRAY
);
