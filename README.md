# employeesystem
Application to manage employee data, payroll

#NOTES

You need to precreate employees database in the postgres DB server  CREATE DATABASE employees;



#INDEX NOTES STARTING


In table employees , we had 801 rows and we ran select query to scan for last row with first_name Ashiq800;

**STEP 1** ::: Select query with first_name without pg index for first_name column

 EXPLAIN ANALYZE SELECT * FROM employees where first_name='Ashiq800';
                                             QUERY PLAN
-----------------------------------------------------------------------------------------------------
 Seq Scan on employees  (cost=0.00..24.01 rows=1 width=91) (actual time=0.187..0.188 rows=1 loops=1)
   Filter: ((first_name)::text = 'Ashiq800'::text)
   Rows Removed by Filter: 800
 Planning Time: 0.140 ms
 Execution Time: 0.213 m
 As you can see it scanned all the rows which is 801 and discarded 800 rows
-----------------------------

**STEP 2** ::

Lets Check what is default block size in postgres
employees=# SELECT current_setting('block_size');
 current_setting
 
  8192
(1 row)
8192 bytes
-----------------
**STEP 3** :::
Lets create unique index for first_name

You can not create index on a column having duplicate values present

employees=# CREATE UNIQUE INDEX idx_first_name ON public.employees USING btree (first_name);
ERROR:  could not create unique index "idx_first_name"
DETAIL:  Key (first_name)=(Ashiq) is duplicated.

DELETE FROM employees WHERE "first_name"='Ashiq';

employees=# CREATE UNIQUE INDEX idx_first_name ON public.employees USING btree (first_name);
CREATE INDEX

employees=# Select * from pg_indexes where tablename = 'employees';
 schemaname | tablename |          indexname           | tablespace |                                           indexdef

------------+-----------+------------------------------+------------+----------------------------------------------------------------------------------------------
 public     | employees | employees_pkey               |            | CREATE UNIQUE INDEX employees_pkey ON public.employees USING btree (empid)
 public     | employees | uk_j9xgmd0ya5jmus09o0b8pqrpb |            | CREATE UNIQUE INDEX uk_j9xgmd0ya5jmus09o0b8pqrpb ON public.employees USING btree (email)
 public     | employees | uk_968bmvh68d9mn4n1tomcml6d8 |            | CREATE UNIQUE INDEX uk_968bmvh68d9mn4n1tomcml6d8 ON public.employees USING btree (mobile_no)
 public     | employees | idx_first_name               |            | CREATE UNIQUE INDEX idx_first_name ON public.employees USING btree (first_name)
(4 rows)
----------------

**STEP 4**  ::: Now run the select query with index created on first_name
 
employees=# EXPLAIN ANALYZE SELECT * FROM employees where first_name='Ashiq800';
                                                        QUERY PLAN

---------------------------------------------------------------------------------------------------------------------------
 Index Scan using idx_first_name on employees  (cost=0.28..8.29 rows=1 width=91) (actual time=0.053..0.054 rows=1 loops=1)
   Index Cond: ((first_name)::text = 'Ashiq800'::text)
 Planning Time: 1.626 ms
 Execution Time: 0.072 ms   **Execution time is reduced in STEP 4 as compared to STEP 1 execution time 0.213 ms**
 

 

