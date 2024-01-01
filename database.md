### Database
* MySQL - As the relationship between data is clearly defined.

### Schema

#### TableName : tree
```
* id : BIGINT
* parent_id : BIGINT
* label : VARCHAR(200)
* created_date : DATETIME

| id  | parent_id | label |
|-----|:---------:|------:|
| 1   |   NULL    |     a |
| 2   |     1     |     b |
| 3   |     1     |     c |
| 4   |     3     |     d |
| 5   |     4     |     e |
```

### Queries

Fetch all the n-level child records starting from parent
```
SELECT @id := id FROM tree WHERE parent_id is null;
WITH RECURSIVE complete_tree AS (
SELECT t.id, t.parent_id, t.label FROM tree t WHERE id = @id
UNION ALL
SELECT t.id, t.parent_id, t.label FROM tree t JOIN complete_tree ct ON t.parent_id = ct.id
) SELECT id, parent_id, label FROM complete_tree;
```