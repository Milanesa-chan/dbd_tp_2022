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