SELECT pp.name, mm.name FROM library AS pp JOIN author AS mm ON pp.id = mm.library_id;

SELECT pp.name "Название произведения", mm.name Автор FROM library AS pp JOIN author AS mm ON pp.id = mm.library_id;

SELECT pp.name AS Название, mm.name AS Автор FROM library pp JOIN author mm ON pp.id = mm.library_id;