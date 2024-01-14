/*SELECT 'CREATE DATABASE diti5_springboot2'
    WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'diti5_springboot2') gexec*/

-- https://stackoverflow.com/questions/39280340/how-to-run-sql-scripts-and-get-data-on-application-startup


CREATE OR REPLACE VIEW categorie_view AS
WITH RECURSIVE RecursiveTable AS (
    SELECT
        categorie.id,
        categorie.categorie_id,
        1 AS depth
    FROM
        categorie
    WHERE
        categorie.categorie_id IS NULL
    UNION ALL
    SELECT
        t.id,
        t.categorie_id,
        rt.depth + 1
    FROM
        categorie t
            JOIN
        RecursiveTable rt ON t.categorie_id = rt.id
)
SELECT
    RecursiveTable.id,
    RecursiveTable.categorie_id,
    RecursiveTable.depth,
    categorie.description,
    (SELECT COALESCE(COUNT(*), 0) FROM produit WHERE produit.categorie_id = RecursiveTable.id) AS nbre_produit,
    categorie.nom
FROM
    RecursiveTable
        JOIN
    categorie ON RecursiveTable.id = categorie.id;


-- Utilisez $custom$ comme délimiteur pour le bloc de texte

DROP FUNCTION IF EXISTS public."getDepthForCategorieId"(IN categorie_id bigint);

CREATE OR REPLACE FUNCTION public."getDepthForCategorieId"(IN categorie_id bigint)
    RETURNS numeric AS
    $$
        DECLARE retour numeric = 0;
        BEGIN
            IF $1 IS NOT NULL THEN
                WITH RECURSIVE RecursiveTable AS
                                   ( SELECT categorie.id, categorie.categorie_id, 1 AS depth
                                     FROM categorie
                                     WHERE categorie.categorie_id IS NULL
                                     UNION ALL
                                     SELECT
                                         t.id,
                                         t.categorie_id,
                                         rt.depth + 1
                                     FROM categorie t
                                              JOIN RecursiveTable rt ON t.categorie_id = rt.id
                                   )
                SELECT depth FROM RecursiveTable WHERE id = $1 into retour;
            END IF;
            RETURN retour;
        END
    $$
    LANGUAGE plpgsql;


ALTER FUNCTION public."getDepthForCategorieId"(IN categorie_id bigint)
    OWNER TO postgres;


DROP FUNCTION IF EXISTS public."getNbreProduitForCategorieId"(IN categorie_id bigint);

CREATE OR REPLACE FUNCTION public."getNbreProduitForCategorieId"(IN categorie_id bigint)
    RETURNS numeric AS
    $$
        DECLARE retour numeric = 0;
        BEGIN
            IF $1 IS NOT NULL THEN
                SELECT COALESCE(COUNT(*), 0) FROM produit WHERE produit.categorie_id = $1 into retour;
            END IF;
            RETURN retour;
        END
    $$
    LANGUAGE plpgsql;


ALTER FUNCTION public."getNbreProduitForCategorieId"(IN categorie_id bigint)
    OWNER TO postgres;
