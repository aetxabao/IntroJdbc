-- items cuyo estado es aceptado y no están en el histórico
SELECT id, nombre, `desc`, precioInicio, urlImagen, nombreUsuario, estado, historico
FROM utx_db.items
WHERE estado = 1 AND historico = false
ORDER BY id DESC;

-- comprobar si el item existe
SELECT * FROM utx_db.items WHERE id = ?;

-- seleccionar un item por id
SELECT id, nombre, `desc`, precioInicio, urlImagen, nombreUsuario, estado, historico
FROM utx_db.items
WHERE id = ?;

-- insertar un item
INSERT INTO utx_db.items (nombre, `desc`, precioInicio, urlImagen, nombreUsuario, estado, historico)
VALUES (?, ?, ?, ?, ?, ?, ?);

-- actualizar un item
UPDATE utx_db.items
SET nombre = ?, `desc` = ?, precioInicio = ?, urlImagen = ?, nombreUsuario = ?, estado = ?, historico = ?
WHERE id = ?;

-- eliminar un item
DELETE FROM utx_db.items WHERE id = ?;

-- eliminar todos los items
DELETE FROM utx_db.items;


-- poner todos los items en histórico
UPDATE utx_db.items
SET historico = true;

-- seleccionar todos los items en cualquier estado independientemente de si están en histórico
SELECT * FROM utx_db.items;

-- delete items en estado cancelado
DELETE FROM utx_db.items WHERE estado = -2;
