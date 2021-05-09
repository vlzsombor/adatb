create or replace TRIGGER del_Felhasznalo
BEFORE DELETE
ON Felhasznalo
FOR EACH ROW
BEGIN
    DELETE Kep WHERE Kep.felhasznaloid = :OLD.Id; -- Ez minden a képpel kapcsolatos dolgot is töröl majd
    DELETE Velemeny WHERE Velemeny.felhasznaloid = :OLD.Id; -- Ez minden a véleménnyel kapcsolatos dolgot is töröl majd
    DELETE Ertekeles WHERE Ertekeles.felhasznaloid = :OLD.Id;
    -- DELETE Topic WHERE Topic.felhasznaloid = :OLD.Id; -- Ez törli a Topichoz tartozó hozzászólásokat, ami meg majd törli az azokra érkezett válaszokat
   --  DELETE ForumHozzaszolas WHERE ForumHozzaszolas.felhasznaloid = :OLD.Id; -- Ez törli a hozzászólásra érkezett válaszokat is
    -- DELETE ForumHozzaszolasValasz WHERE ForumHozzaszolasValasz.felhasznaloid = :OLD.Id;
    -- DELETE MegtekintettKepek WHERE MegtekintettKepek.felhasznaloid = :OLD.Id;
    -- DELETE Palyazat WHERE Palyazat.felhasznaloid = :OLD.Id; -- Ez törli a PalyazatKep-ből az adott pályázathoz tartozó képeket, de a Kep-ben megmarad a kép
    -- DELETE PalyazatKepSzavazatok WHERE PalyazatKepSzavazatok.felhasznaloid = :OLD.Id;
    -- DELETE Reportok WHERE Reportok.felhasznaloid = :OLD.Id;
END;



create or replace TRIGGER DEL_KEP
BEFORE DELETE
ON Kep
FOR EACH ROW
BEGIN
    -- DELETE Reportok WHERE Reportok.kepid = :OLD.Id;
    -- DELETE PalyazatKep WHERE PalyazatKep.kepid = :OLD.Id ;-- ez törli majd a szavazatokat is
    -- DELETE MegtekintettKepek WHERE MegtekintettKepek.kepid = :OLD.Id; -- itt talán updatelni
    DELETE Ertekeles WHERE Ertekeles.kepid = :OLD.Id;
    DELETE Velemeny WHERE Velemeny.kepid = :OLD.Id; -- ez törli majd a Véleményekre érkezett válaszokat is
    DELETE REPORT WHERE Report.KEP_ID = :OLD.Id;
END;