# Presto
공식 홈페이지를 털어보자
[공홈 링크](https://prestodb.io/docs/current/index.html)

## [10.34. SELECT](https://prestodb.io/docs/current/sql/select.html)
### Synopsis
```
[ WITH with_query [, ...] ]
SELECT [ ALL | DISTINCT ] select_expr [, ...]
[ FROM from_item [, ...] ]
[ WHERE condition ]
[ GROUP BY [ ALL | DISTINCT ] grouping_element [, ...] ]
[ HAVING condition]
[ { UNION | INTERSECT | EXCEPT } [ ALL | DISTINCT ] select ]
[ ORDER BY expression [ ASC | DESC ] [, ...] ]
[ LIMIT [ count | ALL ] ]
```

where ```from_item``` is one of

```
table_name [ [ AS ] alias [ ( column_alias [, ...] ) ] ]
```
```
from_item join_type from_item [ ON join_condition | USING ( join_column [, ...] ) ]
```

and ```join_type```is one of

```
[ INNER ] JOIN
LEFT [ OUTER ] JOIN
RIGHT [ OUTER ] JOIN
FULL [ OUTER ] JOIN
CROSS JOIN
```

and ```grouping_element```is one of

```
()
expression
GROUPING SETS ( ( column [, ...] ) [, ...] )
CUBE ( column [, ...] )
ROLLUP ( column [, ...] )
```

뭐시가 이리 어렵누...

### Description
0개 이상의 테이블에서 실헹

### WITH Clause
```WITH```구문은 쿼리에서 정의된 관계를 정의된다. 
중접 된 쿼리를 병합하거나 하위 쿼리를 단순화 할 수 있다.

예시
```
SELECT a, b
FROM (
    SELECT a, MAX(b) AS b FROM t GROUP BY a
) AS x;

WITH x AS (SELECT a, MAX(b) AS b FROM t GROUP BY a)
SELECT a, b FROM x;
```
위 쿼리를 아래처럼 작성할 수 있다.

```
WITH 
    t1 AS (SELECT a, MAX(b) AD b FROM x GROUP BY a),
    t2 AS (SELECT a, AVG(d) AS d FROM y GROUP BY a)
SELECT t1.*, t2.*
FROM t1
JOIN t2 ON t1.a = t2.a;
```

또한 ```WITH`` 절 내의 관계는 다름을 연결할 수 있다.

```
WITH
    x AS ( SELECT a FROM t ),
    y AS ( SELECT a AS b FROM x ),
    z AS ( SELECT b AS c FROM y )
SELECT c FROM z ;
```
 -> 현재 ```WITH```절에 대한 SQL은 명명된 관계가 사용되는 모든 곳에서 인라인된다.
    즉, 관계가 두 번 이상 사용되고 쿼리가 비 결정적이면 결과가 매번 다를 수 있다.