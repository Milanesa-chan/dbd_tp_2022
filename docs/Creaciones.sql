-------------------------
-- INSERCION DE SOCIOS --
-------------------------

CREATE OR REPLACE PROCEDURE insertar_sub_socio (_cod_base integer, _id_socio integer, _tipo "char")
LANGUAGE plpgsql AS $$
BEGIN
    CASE
    WHEN _tipo = 'I' THEN 
        INSERT INTO "Socio_Infantil" (cod_base, id_socio) VALUES (_cod_base, _id_socio); 
    WHEN _tipo = 'M' THEN 
        INSERT INTO "Socio_Mayor" (cod_base, id_socio) VALUES (_cod_base, _id_socio); 
    ELSE 
        INSERT INTO "Socio_Vitalicio" (cod_base, id_socio) VALUES (_cod_base, _id_socio); 
END CASE;
END;
$$;

CREATE OR REPLACE FUNCTION fun_trig_insertar_socio() RETURNS TRIGGER AS $$
    BEGIN
        CALL insertar_sub_socio(NEW.cod_base, NEW.id_socio, NEW.tipo);
        RETURN NEW;
    END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE TRIGGER trigger_insertar_socio AFTER INSERT
ON "Socio"
FOR EACH ROW
EXECUTE PROCEDURE fun_trig_insertar_socio();

-------------------------
-- INSERCION DE VISTAS --
----------------------------
-- Vista que lista todos los titulares que tienen deuda que no han pagado en el año en curso.
  CREATE OR REPLACE VIEW "titulares_deuda_cuota" AS 
   SELECT s.cod_base,
    s.nombre,
    s.apellido,
    j.deuda_cuota_social,
    j.cant_integrantes
   FROM "Socio" s
     JOIN ( SELECT g.cod_base,
            g.deuda_cuota_social,
            count(*) AS cant_integrantes
           FROM "Grupo_Familiar" g
             JOIN "Socio" s_1 ON g.cod_base = s_1.cod_base
          WHERE g.deuda_cuota_social > 0
           AND EXISTS (
             SELECT * from "Cuota_Mensual" cm where id_cuota_mensual not in (SELECT id_cuota_mensual FROM "Pago_Cuota") 
             and DATE_PART('year',fecha_vigencia) = DATE_PART('year',CURRENT_DATE) AND g.cod_base = cm.cod_base)
          GROUP BY g.cod_base) j ON j.cod_base = s.cod_base
  WHERE s.id_socio = 0;

--------------
-- TRIGGERS --
--------------

-- CAPACIDAD DE UN PROFESIONAL PARA ESTAR A CARGO DE UNA ACTIVIDAD --

CREATE OR REPLACE FUNCTION prof_capacitado_para_turno() RETURNS TRIGGER
LANGUAGE plpgsql AS $$
BEGIN
	IF NOT EXISTS (SELECT * FROM "Turno" T INNER JOIN "Capacitado_Para" CP
ON T.id_actividad = CP.id_actividad
				   WHERE T.id_turno = NEW.id_turno AND CP.id_profesional = NEW.id_profesional)
	THEN
		RAISE EXCEPTION 'El profesional no esta capacitado para esa actividad';
	END IF;
	RETURN NEW;
END;
$$;

CREATE TRIGGER capacidad_profesional BEFORE INSERT ON "A_Cargo_De"
FOR EACH ROW EXECUTE FUNCTION prof_capacitado_para_turno();
