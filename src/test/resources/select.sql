#2021-06-09
#https://stackoverflow.com/questions/149772/how-to-use-group-by-to-concatenate-strings-in-mysql
SELECT grade, GROUP_CONCAT(distinct class_id order by class_id SEPARATOR ',') FROM table_t where ts_id in('14','15','16')  GROUP BY grade;

#https://stackoverflow.com/questions/3710483/select-where-count-of-one-field-is-greater-than-one
https://thispointer.com/mysql-select-where-count-is-greater-than-one-solved/#:~:text=Select%20where%20count%20is%20greater%20than%20one%20%3A,%3Cyour_table%3E%20GROUP%20BY%20%3Cyour_column_name%3E%20HAVING%20COUNT%20%3Canother_column_name%3E%20Example%3A-
select m_id,l_id,count(l_id) from table_t group by l_id having count(l_id) > 1
