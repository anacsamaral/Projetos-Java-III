
SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 7 (class 2615 OID 2200)
-- Name: public; Type: SCHEMA; Schema: -; Owner: postgres
--

-- *not* creating schema, since initdb creates it


ALTER SCHEMA public OWNER TO postgres;

SET default_tablespace = '';

--
-- TOC entry 196 (class 1259 OID 238589)
-- Name: autor; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.autor (
    aut_id integer NOT NULL,
    aut_nome character varying(40),
    aut_nacionalidade character varying(30)
);


ALTER TABLE public.autor OWNER TO postgres;

--
-- TOC entry 197 (class 1259 OID 238592)
-- Name: autor_aut_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.autor_aut_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.autor_aut_id_seq OWNER TO postgres;

--
-- TOC entry 2850 (class 0 OID 0)
-- Dependencies: 197
-- Name: autor_aut_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.autor_aut_id_seq OWNED BY public.autor.aut_id;


--
-- TOC entry 198 (class 1259 OID 238594)
-- Name: capitulo; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.capitulo (
    cap_id integer NOT NULL,
    cap_titulo character varying(80),
    cap_pagina integer,
    liv_id integer
);


ALTER TABLE public.capitulo OWNER TO postgres;

--
-- TOC entry 199 (class 1259 OID 238597)
-- Name: capitulo_cap_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.capitulo_cap_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.capitulo_cap_id_seq OWNER TO postgres;

--
-- TOC entry 2851 (class 0 OID 0)
-- Dependencies: 199
-- Name: capitulo_cap_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.capitulo_cap_id_seq OWNED BY public.capitulo.cap_id;


--
-- TOC entry 200 (class 1259 OID 238599)
-- Name: editora; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.editora (
    edi_id integer NOT NULL,
    edi_nome character varying(25),
    edi_local character varying(20)
);


ALTER TABLE public.editora OWNER TO postgres;

--
-- TOC entry 201 (class 1259 OID 238602)
-- Name: editora_edi_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.editora_edi_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.editora_edi_id_seq OWNER TO postgres;

--
-- TOC entry 2852 (class 0 OID 0)
-- Dependencies: 201
-- Name: editora_edi_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.editora_edi_id_seq OWNED BY public.editora.edi_id;


--
-- TOC entry 202 (class 1259 OID 238604)
-- Name: livro; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.livro (
    liv_id integer NOT NULL,
    liv_titulo character varying(80),
    edi_id integer
);


ALTER TABLE public.livro OWNER TO postgres;

--
-- TOC entry 203 (class 1259 OID 238607)
-- Name: livro_autor; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.livro_autor (
    la_id integer NOT NULL,
    liv_id integer,
    aut_id integer
);


ALTER TABLE public.livro_autor OWNER TO postgres;

--
-- TOC entry 204 (class 1259 OID 238610)
-- Name: livro_autor_la_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.livro_autor_la_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.livro_autor_la_id_seq OWNER TO postgres;

--
-- TOC entry 2853 (class 0 OID 0)
-- Dependencies: 204
-- Name: livro_autor_la_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.livro_autor_la_id_seq OWNED BY public.livro_autor.la_id;


--
-- TOC entry 205 (class 1259 OID 238612)
-- Name: livro_liv_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.livro_liv_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.livro_liv_id_seq OWNER TO postgres;

--
-- TOC entry 2854 (class 0 OID 0)
-- Dependencies: 205
-- Name: livro_liv_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.livro_liv_id_seq OWNED BY public.livro.liv_id;


--
-- TOC entry 2694 (class 2604 OID 238614)
-- Name: autor aut_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.autor ALTER COLUMN aut_id SET DEFAULT nextval('public.autor_aut_id_seq'::regclass);


--
-- TOC entry 2695 (class 2604 OID 238615)
-- Name: capitulo cap_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.capitulo ALTER COLUMN cap_id SET DEFAULT nextval('public.capitulo_cap_id_seq'::regclass);


--
-- TOC entry 2696 (class 2604 OID 238616)
-- Name: editora edi_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.editora ALTER COLUMN edi_id SET DEFAULT nextval('public.editora_edi_id_seq'::regclass);


--
-- TOC entry 2697 (class 2604 OID 238617)
-- Name: livro liv_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.livro ALTER COLUMN liv_id SET DEFAULT nextval('public.livro_liv_id_seq'::regclass);


--
-- TOC entry 2698 (class 2604 OID 238618)
-- Name: livro_autor la_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.livro_autor ALTER COLUMN la_id SET DEFAULT nextval('public.livro_autor_la_id_seq'::regclass);


--
-- TOC entry 2834 (class 0 OID 238589)
-- Dependencies: 196
-- Data for Name: autor; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.autor VALUES (1, 'Alice Programmer', 'Brasileiro');
INSERT INTO public.autor VALUES (2, 'Bob Developer', 'Brasileiro');
INSERT INTO public.autor VALUES (3, 'Carlos Engineer', 'Brasileiro');
INSERT INTO public.autor VALUES (4, 'Daniel Data', 'Brasileiro');
INSERT INTO public.autor VALUES (5, 'Eve Designer', 'Brasileiro');


--
-- TOC entry 2836 (class 0 OID 238594)
-- Dependencies: 198
-- Data for Name: capitulo; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.capitulo VALUES (1, 'Capítulo 1: Introdução ao Python', 1, 1);
INSERT INTO public.capitulo VALUES (2, 'Capítulo 2: Estruturas de Controle', 10, 1);
INSERT INTO public.capitulo VALUES (3, 'Capítulo 1: Introdução ao Java', 1, 2);
INSERT INTO public.capitulo VALUES (4, 'Capítulo 2: Sintaxe Básica', 12, 2);
INSERT INTO public.capitulo VALUES (5, 'Capítulo 1: O que é HTML?', 1, 3);
INSERT INTO public.capitulo VALUES (6, 'Capítulo 2: Estilizando com CSS', 15, 3);
INSERT INTO public.capitulo VALUES (7, 'Capítulo 1: Introdução ao JavaScript', 1, 4);
INSERT INTO public.capitulo VALUES (8, 'Capítulo 2: Manipulação do DOM', 20, 4);
INSERT INTO public.capitulo VALUES (9, 'Capítulo 1: Algoritmos Básicos', 1, 5);
INSERT INTO public.capitulo VALUES (10, 'Capítulo 2: Estruturas de Dados', 18, 5);
INSERT INTO public.capitulo VALUES (11, 'Capítulo 1: Configurando o Ambiente', 5, 6);
INSERT INTO public.capitulo VALUES (12, 'Capítulo 2: Classes e Objetos', 25, 6);
INSERT INTO public.capitulo VALUES (13, 'Capítulo 1: Criando uma Aplicação em C#', 1, 7);
INSERT INTO public.capitulo VALUES (14, 'Capítulo 2: LINQ e Banco de Dados', 14, 7);
INSERT INTO public.capitulo VALUES (15, 'Capítulo 1: PHP Básico', 3, 8);
INSERT INTO public.capitulo VALUES (16, 'Capítulo 2: Interagindo com MySQL', 30, 8);
INSERT INTO public.capitulo VALUES (17, 'Capítulo 1: Introdução à Machine Learning', 5, 9);
INSERT INTO public.capitulo VALUES (18, 'Capítulo 2: Aplicações Práticas', 28, 9);
INSERT INTO public.capitulo VALUES (19, 'Capítulo 1: Testes Unitários', 6, 10);
INSERT INTO public.capitulo VALUES (20, 'Capítulo 2: Testes de Integração', 18, 10);
INSERT INTO public.capitulo VALUES (21, 'Capítulo 1: Scrum e Kanban', 4, 11);
INSERT INTO public.capitulo VALUES (22, 'Capítulo 2: Gestão de Projetos', 30, 11);
INSERT INTO public.capitulo VALUES (23, 'Capítulo 1: Programação Orientada a Objetos', 2, 12);
INSERT INTO public.capitulo VALUES (24, 'Capítulo 2: Herança e Polimorfismo', 12, 12);
INSERT INTO public.capitulo VALUES (25, 'Capítulo 1: Os Fundamentos de Programação', 4, 13);
INSERT INTO public.capitulo VALUES (26, 'Capítulo 2: Abordagem de Recurso', 8, 13);
INSERT INTO public.capitulo VALUES (27, 'Capítulo 1: Coleta de Dados', 7, 14);
INSERT INTO public.capitulo VALUES (28, 'Capítulo 2: Limpeza de Dados', 19, 14);
INSERT INTO public.capitulo VALUES (29, 'Capítulo 1: Princípios da Segurança', 3, 15);
INSERT INTO public.capitulo VALUES (30, 'Capítulo 2: Criptografia', 9, 15);
INSERT INTO public.capitulo VALUES (31, 'Capítulo 1: Introdução ao Flutter', 6, 16);
INSERT INTO public.capitulo VALUES (32, 'Capítulo 2: Widgets e Layouts', 14, 16);
INSERT INTO public.capitulo VALUES (33, 'Capítulo 1: O Futuro da Inteligência Artificial', 12, 17);
INSERT INTO public.capitulo VALUES (34, 'Capítulo 2: Casos de Uso em Python', 22, 17);
INSERT INTO public.capitulo VALUES (35, 'Capítulo 1: Criando Interfaces com React', 18, 18);
INSERT INTO public.capitulo VALUES (36, 'Capítulo 2: Gerenciamento de Estado', 16, 18);


--
-- TOC entry 2838 (class 0 OID 238599)
-- Dependencies: 200
-- Data for Name: editora; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.editora VALUES (1, 'Informática Editora', 'São Paulo');
INSERT INTO public.editora VALUES (2, 'TechBooks', 'Rio de Janeiro');
INSERT INTO public.editora VALUES (3, 'EducaTech', 'Curitiba');
INSERT INTO public.editora VALUES (4, 'Programar Editora', 'Belo Horizonte');
INSERT INTO public.editora VALUES (5, 'Code & Learn', 'Porto Alegre');


--
-- TOC entry 2840 (class 0 OID 238604)
-- Dependencies: 202
-- Data for Name: livro; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.livro VALUES (1, 'Aprendendo Python', 1);
INSERT INTO public.livro VALUES (2, 'Fundamentos de Java', 1);
INSERT INTO public.livro VALUES (3, 'Desenvolvimento Web com HTML e CSS', 2);
INSERT INTO public.livro VALUES (4, 'JavaScript para Iniciantes', 2);
INSERT INTO public.livro VALUES (5, 'Algoritmos e Estruturas de Dados', 3);
INSERT INTO public.livro VALUES (6, 'Introdução ao C++', 3);
INSERT INTO public.livro VALUES (7, 'Desenvolvendo Aplicações com C#', 4);
INSERT INTO public.livro VALUES (8, 'Banco de Dados para Desenvolvedores', 4);
INSERT INTO public.livro VALUES (9, 'PHP e MySQL', 5);
INSERT INTO public.livro VALUES (10, 'Programação Funcional com Scala', 5);
INSERT INTO public.livro VALUES (11, 'Machine Learning com R', 1);
INSERT INTO public.livro VALUES (12, 'Testes Automatizados com JUnit', 1);
INSERT INTO public.livro VALUES (13, 'Metodologias Ágeis para Programadores', 2);
INSERT INTO public.livro VALUES (14, 'Programação Orientada a Objetos em Python', 2);
INSERT INTO public.livro VALUES (15, 'A Arte da Programação', 3);
INSERT INTO public.livro VALUES (16, 'Python para Ciência de Dados', 3);
INSERT INTO public.livro VALUES (17, 'Segurança da Informação em Desenvolvimento', 4);
INSERT INTO public.livro VALUES (18, 'Desenvolvimento Mobile com Flutter', 4);
INSERT INTO public.livro VALUES (19, 'Inteligência Artificial com Python', 5);
INSERT INTO public.livro VALUES (20, 'Interface de Usuário com React', 5);


--
-- TOC entry 2841 (class 0 OID 238607)
-- Dependencies: 203
-- Data for Name: livro_autor; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.livro_autor VALUES (1, 1, 1);
INSERT INTO public.livro_autor VALUES (2, 2, 1);
INSERT INTO public.livro_autor VALUES (3, 3, 2);
INSERT INTO public.livro_autor VALUES (4, 4, 2);
INSERT INTO public.livro_autor VALUES (5, 5, 3);
INSERT INTO public.livro_autor VALUES (6, 6, 3);
INSERT INTO public.livro_autor VALUES (7, 7, 4);
INSERT INTO public.livro_autor VALUES (8, 8, 4);
INSERT INTO public.livro_autor VALUES (9, 9, 5);
INSERT INTO public.livro_autor VALUES (10, 10, 5);
INSERT INTO public.livro_autor VALUES (11, 11, 1);
INSERT INTO public.livro_autor VALUES (12, 12, 1);
INSERT INTO public.livro_autor VALUES (13, 13, 2);
INSERT INTO public.livro_autor VALUES (14, 14, 2);
INSERT INTO public.livro_autor VALUES (15, 15, 3);
INSERT INTO public.livro_autor VALUES (16, 16, 3);
INSERT INTO public.livro_autor VALUES (17, 17, 4);
INSERT INTO public.livro_autor VALUES (18, 18, 4);
INSERT INTO public.livro_autor VALUES (19, 19, 5);
INSERT INTO public.livro_autor VALUES (20, 20, 5);


--
-- TOC entry 2855 (class 0 OID 0)
-- Dependencies: 197
-- Name: autor_aut_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.autor_aut_id_seq', 5, true);


--
-- TOC entry 2856 (class 0 OID 0)
-- Dependencies: 199
-- Name: capitulo_cap_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.capitulo_cap_id_seq', 36, true);


--
-- TOC entry 2857 (class 0 OID 0)
-- Dependencies: 201
-- Name: editora_edi_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.editora_edi_id_seq', 5, true);


--
-- TOC entry 2858 (class 0 OID 0)
-- Dependencies: 204
-- Name: livro_autor_la_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.livro_autor_la_id_seq', 20, true);


--
-- TOC entry 2859 (class 0 OID 0)
-- Dependencies: 205
-- Name: livro_liv_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.livro_liv_id_seq', 20, true);


--
-- TOC entry 2700 (class 2606 OID 238620)
-- Name: autor autor_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.autor
    ADD CONSTRAINT autor_pkey PRIMARY KEY (aut_id);


--
-- TOC entry 2702 (class 2606 OID 238622)
-- Name: capitulo capitulo_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.capitulo
    ADD CONSTRAINT capitulo_pkey PRIMARY KEY (cap_id);


--
-- TOC entry 2704 (class 2606 OID 238624)
-- Name: editora editora_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.editora
    ADD CONSTRAINT editora_pkey PRIMARY KEY (edi_id);


--
-- TOC entry 2708 (class 2606 OID 238626)
-- Name: livro_autor livro_autor_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.livro_autor
    ADD CONSTRAINT livro_autor_pkey PRIMARY KEY (la_id);


--
-- TOC entry 2706 (class 2606 OID 238628)
-- Name: livro livro_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.livro
    ADD CONSTRAINT livro_pkey PRIMARY KEY (liv_id);


--
-- TOC entry 2709 (class 2606 OID 238629)
-- Name: capitulo capitulo_liv_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.capitulo
    ADD CONSTRAINT capitulo_liv_id_fkey FOREIGN KEY (liv_id) REFERENCES public.livro(liv_id);


--
-- TOC entry 2711 (class 2606 OID 238634)
-- Name: livro_autor livro_autor_aut_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.livro_autor
    ADD CONSTRAINT livro_autor_aut_id_fkey FOREIGN KEY (aut_id) REFERENCES public.autor(aut_id) NOT VALID;


--
-- TOC entry 2712 (class 2606 OID 238639)
-- Name: livro_autor livro_autor_liv_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.livro_autor
    ADD CONSTRAINT livro_autor_liv_id_fkey FOREIGN KEY (liv_id) REFERENCES public.livro(liv_id) NOT VALID;


--
-- TOC entry 2710 (class 2606 OID 238644)
-- Name: livro livro_edi_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.livro
    ADD CONSTRAINT livro_edi_id_fkey FOREIGN KEY (edi_id) REFERENCES public.editora(edi_id) NOT VALID;


--
-- TOC entry 2849 (class 0 OID 0)
-- Dependencies: 7
-- Name: SCHEMA public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE USAGE ON SCHEMA public FROM PUBLIC;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2025-11-24 21:03:35

--
-- PostgreSQL database dump complete
--

