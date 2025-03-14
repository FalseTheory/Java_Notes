INSERT INTO NOTES("id","title","content")
VALUES(1,'Заголовок','Образец заметки')
ON CONFLICT DO NOTHING;