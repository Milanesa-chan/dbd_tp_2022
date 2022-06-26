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