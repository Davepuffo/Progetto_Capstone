PGDMP     ,    !                {           capstone    15.2    15.2 O    k           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            l           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            m           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            n           1262    39356    capstone    DATABASE     {   CREATE DATABASE capstone WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Italian_Italy.1252';
    DROP DATABASE capstone;
                postgres    false            �            1259    80269    articoli    TABLE     �  CREATE TABLE public.articoli (
    animale smallint NOT NULL,
    prezzo double precision NOT NULL,
    tipo smallint NOT NULL,
    id bigint NOT NULL,
    descrizione character varying(255),
    foto character varying(255) NOT NULL,
    nome character varying(255) NOT NULL,
    CONSTRAINT articoli_animale_check CHECK (((animale >= 0) AND (animale <= 4))),
    CONSTRAINT articoli_tipo_check CHECK (((tipo >= 0) AND (tipo <= 1)))
);
    DROP TABLE public.articoli;
       public         heap    postgres    false            �            1259    80268    articoli_id_seq    SEQUENCE     x   CREATE SEQUENCE public.articoli_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public.articoli_id_seq;
       public          postgres    false    215            o           0    0    articoli_id_seq    SEQUENCE OWNED BY     C   ALTER SEQUENCE public.articoli_id_seq OWNED BY public.articoli.id;
          public          postgres    false    214            �            1259    80282    fatture    TABLE     G   CREATE TABLE public.fatture (
    data date,
    id bigint NOT NULL
);
    DROP TABLE public.fatture;
       public         heap    postgres    false            �            1259    80281    fatture_id_seq    SEQUENCE     w   CREATE SEQUENCE public.fatture_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 %   DROP SEQUENCE public.fatture_id_seq;
       public          postgres    false    217            p           0    0    fatture_id_seq    SEQUENCE OWNED BY     A   ALTER SEQUENCE public.fatture_id_seq OWNED BY public.fatture.id;
          public          postgres    false    216            �            1259    80288    fatture_user    TABLE     b   CREATE TABLE public.fatture_user (
    fattura_id bigint NOT NULL,
    user_id bigint NOT NULL
);
     DROP TABLE public.fatture_user;
       public         heap    postgres    false            �            1259    80294 	   indirizzi    TABLE     �  CREATE TABLE public.indirizzi (
    cap integer NOT NULL,
    id bigint NOT NULL,
    user_id bigint,
    citta character varying(255) NOT NULL,
    civico character varying(255),
    provincia character varying(255) NOT NULL,
    tipo character varying(255) NOT NULL,
    via character varying(255) NOT NULL,
    CONSTRAINT indirizzi_tipo_check CHECK (((tipo)::text = ANY ((ARRAY['FATTURAZIONE'::character varying, 'CONSEGNA'::character varying])::text[])))
);
    DROP TABLE public.indirizzi;
       public         heap    postgres    false            �            1259    80293    indirizzi_id_seq    SEQUENCE     y   CREATE SEQUENCE public.indirizzi_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 '   DROP SEQUENCE public.indirizzi_id_seq;
       public          postgres    false    220            q           0    0    indirizzi_id_seq    SEQUENCE OWNED BY     E   ALTER SEQUENCE public.indirizzi_id_seq OWNED BY public.indirizzi.id;
          public          postgres    false    219            �            1259    80303    indirizzi_user    TABLE     f   CREATE TABLE public.indirizzi_user (
    indirizzo_id bigint NOT NULL,
    user_id bigint NOT NULL
);
 "   DROP TABLE public.indirizzi_user;
       public         heap    postgres    false            �            1259    80309    ordini    TABLE     �  CREATE TABLE public.ordini (
    data date NOT NULL,
    fattura_id bigint,
    id bigint NOT NULL,
    user_id bigint,
    pagamento character varying(255),
    stato character varying(255) NOT NULL,
    CONSTRAINT ordini_stato_check CHECK (((stato)::text = ANY ((ARRAY['CREATO'::character varying, 'CONFERMATO'::character varying, 'IN_PREPARAZIONE'::character varying, 'IN_TRANSITO'::character varying, 'CONSEGNATO'::character varying])::text[])))
);
    DROP TABLE public.ordini;
       public         heap    postgres    false            �            1259    80320    ordini_articoli_ordinati    TABLE     z   CREATE TABLE public.ordini_articoli_ordinati (
    articoli_ordinati_id bigint NOT NULL,
    ordini_id bigint NOT NULL
);
 ,   DROP TABLE public.ordini_articoli_ordinati;
       public         heap    postgres    false            �            1259    80308    ordini_id_seq    SEQUENCE     v   CREATE SEQUENCE public.ordini_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 $   DROP SEQUENCE public.ordini_id_seq;
       public          postgres    false    223            r           0    0    ordini_id_seq    SEQUENCE OWNED BY     ?   ALTER SEQUENCE public.ordini_id_seq OWNED BY public.ordini.id;
          public          postgres    false    222            �            1259    80323    ordini_user    TABLE     `   CREATE TABLE public.ordini_user (
    ordine_id bigint NOT NULL,
    user_id bigint NOT NULL
);
    DROP TABLE public.ordini_user;
       public         heap    postgres    false            �            1259    80329    roles    TABLE       CREATE TABLE public.roles (
    id bigint NOT NULL,
    role_name character varying(255),
    CONSTRAINT roles_role_name_check CHECK (((role_name)::text = ANY ((ARRAY['ROLE_USER'::character varying, 'ROLE_ADMIN'::character varying, 'ROLE_MODERATOR'::character varying])::text[])))
);
    DROP TABLE public.roles;
       public         heap    postgres    false            �            1259    80328    roles_id_seq    SEQUENCE     u   CREATE SEQUENCE public.roles_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.roles_id_seq;
       public          postgres    false    227            s           0    0    roles_id_seq    SEQUENCE OWNED BY     =   ALTER SEQUENCE public.roles_id_seq OWNED BY public.roles.id;
          public          postgres    false    226            �            1259    80337    users    TABLE       CREATE TABLE public.users (
    id bigint NOT NULL,
    email character varying(255) NOT NULL,
    lastname character varying(255) NOT NULL,
    name character varying(255) NOT NULL,
    password character varying(255) NOT NULL,
    username character varying(255) NOT NULL
);
    DROP TABLE public.users;
       public         heap    postgres    false            �            1259    80336    users_id_seq    SEQUENCE     u   CREATE SEQUENCE public.users_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.users_id_seq;
       public          postgres    false    229            t           0    0    users_id_seq    SEQUENCE OWNED BY     =   ALTER SEQUENCE public.users_id_seq OWNED BY public.users.id;
          public          postgres    false    228            �            1259    80349    users_roles    TABLE     ^   CREATE TABLE public.users_roles (
    role_id bigint NOT NULL,
    user_id bigint NOT NULL
);
    DROP TABLE public.users_roles;
       public         heap    postgres    false            �           2604    80272    articoli id    DEFAULT     j   ALTER TABLE ONLY public.articoli ALTER COLUMN id SET DEFAULT nextval('public.articoli_id_seq'::regclass);
 :   ALTER TABLE public.articoli ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    215    214    215            �           2604    80285 
   fatture id    DEFAULT     h   ALTER TABLE ONLY public.fatture ALTER COLUMN id SET DEFAULT nextval('public.fatture_id_seq'::regclass);
 9   ALTER TABLE public.fatture ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    217    216    217            �           2604    80297    indirizzi id    DEFAULT     l   ALTER TABLE ONLY public.indirizzi ALTER COLUMN id SET DEFAULT nextval('public.indirizzi_id_seq'::regclass);
 ;   ALTER TABLE public.indirizzi ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    220    219    220            �           2604    80312 	   ordini id    DEFAULT     f   ALTER TABLE ONLY public.ordini ALTER COLUMN id SET DEFAULT nextval('public.ordini_id_seq'::regclass);
 8   ALTER TABLE public.ordini ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    222    223    223            �           2604    80332    roles id    DEFAULT     d   ALTER TABLE ONLY public.roles ALTER COLUMN id SET DEFAULT nextval('public.roles_id_seq'::regclass);
 7   ALTER TABLE public.roles ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    227    226    227            �           2604    80340    users id    DEFAULT     d   ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.users_id_seq'::regclass);
 7   ALTER TABLE public.users ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    228    229    229            Y          0    80269    articoli 
   TABLE DATA           V   COPY public.articoli (animale, prezzo, tipo, id, descrizione, foto, nome) FROM stdin;
    public          postgres    false    215   �^       [          0    80282    fatture 
   TABLE DATA           +   COPY public.fatture (data, id) FROM stdin;
    public          postgres    false    217   �i       \          0    80288    fatture_user 
   TABLE DATA           ;   COPY public.fatture_user (fattura_id, user_id) FROM stdin;
    public          postgres    false    218   �i       ^          0    80294 	   indirizzi 
   TABLE DATA           Z   COPY public.indirizzi (cap, id, user_id, citta, civico, provincia, tipo, via) FROM stdin;
    public          postgres    false    220   �i       _          0    80303    indirizzi_user 
   TABLE DATA           ?   COPY public.indirizzi_user (indirizzo_id, user_id) FROM stdin;
    public          postgres    false    221   �i       a          0    80309    ordini 
   TABLE DATA           Q   COPY public.ordini (data, fattura_id, id, user_id, pagamento, stato) FROM stdin;
    public          postgres    false    223   �i       b          0    80320    ordini_articoli_ordinati 
   TABLE DATA           S   COPY public.ordini_articoli_ordinati (articoli_ordinati_id, ordini_id) FROM stdin;
    public          postgres    false    224   j       c          0    80323    ordini_user 
   TABLE DATA           9   COPY public.ordini_user (ordine_id, user_id) FROM stdin;
    public          postgres    false    225   2j       e          0    80329    roles 
   TABLE DATA           .   COPY public.roles (id, role_name) FROM stdin;
    public          postgres    false    227   Oj       g          0    80337    users 
   TABLE DATA           N   COPY public.users (id, email, lastname, name, password, username) FROM stdin;
    public          postgres    false    229   �j       h          0    80349    users_roles 
   TABLE DATA           7   COPY public.users_roles (role_id, user_id) FROM stdin;
    public          postgres    false    230   k       u           0    0    articoli_id_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.articoli_id_seq', 23, true);
          public          postgres    false    214            v           0    0    fatture_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.fatture_id_seq', 1, false);
          public          postgres    false    216            w           0    0    indirizzi_id_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('public.indirizzi_id_seq', 1, false);
          public          postgres    false    219            x           0    0    ordini_id_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('public.ordini_id_seq', 1, false);
          public          postgres    false    222            y           0    0    roles_id_seq    SEQUENCE SET     :   SELECT pg_catalog.setval('public.roles_id_seq', 3, true);
          public          postgres    false    226            z           0    0    users_id_seq    SEQUENCE SET     :   SELECT pg_catalog.setval('public.users_id_seq', 1, true);
          public          postgres    false    228            �           2606    80280    articoli articoli_nome_key 
   CONSTRAINT     U   ALTER TABLE ONLY public.articoli
    ADD CONSTRAINT articoli_nome_key UNIQUE (nome);
 D   ALTER TABLE ONLY public.articoli DROP CONSTRAINT articoli_nome_key;
       public            postgres    false    215            �           2606    80278    articoli articoli_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.articoli
    ADD CONSTRAINT articoli_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.articoli DROP CONSTRAINT articoli_pkey;
       public            postgres    false    215            �           2606    80287    fatture fatture_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.fatture
    ADD CONSTRAINT fatture_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.fatture DROP CONSTRAINT fatture_pkey;
       public            postgres    false    217            �           2606    80292 (   fatture_user fatture_user_fattura_id_key 
   CONSTRAINT     i   ALTER TABLE ONLY public.fatture_user
    ADD CONSTRAINT fatture_user_fattura_id_key UNIQUE (fattura_id);
 R   ALTER TABLE ONLY public.fatture_user DROP CONSTRAINT fatture_user_fattura_id_key;
       public            postgres    false    218            �           2606    80302    indirizzi indirizzi_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.indirizzi
    ADD CONSTRAINT indirizzi_pkey PRIMARY KEY (id);
 B   ALTER TABLE ONLY public.indirizzi DROP CONSTRAINT indirizzi_pkey;
       public            postgres    false    220            �           2606    80307 .   indirizzi_user indirizzi_user_indirizzo_id_key 
   CONSTRAINT     q   ALTER TABLE ONLY public.indirizzi_user
    ADD CONSTRAINT indirizzi_user_indirizzo_id_key UNIQUE (indirizzo_id);
 X   ALTER TABLE ONLY public.indirizzi_user DROP CONSTRAINT indirizzi_user_indirizzo_id_key;
       public            postgres    false    221            �           2606    80319    ordini ordini_fattura_id_key 
   CONSTRAINT     ]   ALTER TABLE ONLY public.ordini
    ADD CONSTRAINT ordini_fattura_id_key UNIQUE (fattura_id);
 F   ALTER TABLE ONLY public.ordini DROP CONSTRAINT ordini_fattura_id_key;
       public            postgres    false    223            �           2606    80317    ordini ordini_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY public.ordini
    ADD CONSTRAINT ordini_pkey PRIMARY KEY (id);
 <   ALTER TABLE ONLY public.ordini DROP CONSTRAINT ordini_pkey;
       public            postgres    false    223            �           2606    80327 %   ordini_user ordini_user_ordine_id_key 
   CONSTRAINT     e   ALTER TABLE ONLY public.ordini_user
    ADD CONSTRAINT ordini_user_ordine_id_key UNIQUE (ordine_id);
 O   ALTER TABLE ONLY public.ordini_user DROP CONSTRAINT ordini_user_ordine_id_key;
       public            postgres    false    225            �           2606    80335    roles roles_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.roles
    ADD CONSTRAINT roles_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.roles DROP CONSTRAINT roles_pkey;
       public            postgres    false    227            �           2606    80423 !   users uk6dotkott2kjsp8vw4d0m25fb7 
   CONSTRAINT     ]   ALTER TABLE ONLY public.users
    ADD CONSTRAINT uk6dotkott2kjsp8vw4d0m25fb7 UNIQUE (email);
 K   ALTER TABLE ONLY public.users DROP CONSTRAINT uk6dotkott2kjsp8vw4d0m25fb7;
       public            postgres    false    229            �           2606    80421 !   users ukr43af9ap4edm43mmtq01oddj6 
   CONSTRAINT     `   ALTER TABLE ONLY public.users
    ADD CONSTRAINT ukr43af9ap4edm43mmtq01oddj6 UNIQUE (username);
 K   ALTER TABLE ONLY public.users DROP CONSTRAINT ukr43af9ap4edm43mmtq01oddj6;
       public            postgres    false    229            �           2606    80346    users users_email_key 
   CONSTRAINT     Q   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_email_key UNIQUE (email);
 ?   ALTER TABLE ONLY public.users DROP CONSTRAINT users_email_key;
       public            postgres    false    229            �           2606    80344    users users_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.users DROP CONSTRAINT users_pkey;
       public            postgres    false    229            �           2606    80353    users_roles users_roles_pkey 
   CONSTRAINT     h   ALTER TABLE ONLY public.users_roles
    ADD CONSTRAINT users_roles_pkey PRIMARY KEY (role_id, user_id);
 F   ALTER TABLE ONLY public.users_roles DROP CONSTRAINT users_roles_pkey;
       public            postgres    false    230    230            �           2606    80348    users users_username_key 
   CONSTRAINT     W   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_username_key UNIQUE (username);
 B   ALTER TABLE ONLY public.users DROP CONSTRAINT users_username_key;
       public            postgres    false    229            �           2606    80404 '   ordini_user fk1tjgi8rfkhdidym530r4r85t1    FK CONSTRAINT     �   ALTER TABLE ONLY public.ordini_user
    ADD CONSTRAINT fk1tjgi8rfkhdidym530r4r85t1 FOREIGN KEY (user_id) REFERENCES public.users(id);
 Q   ALTER TABLE ONLY public.ordini_user DROP CONSTRAINT fk1tjgi8rfkhdidym530r4r85t1;
       public          postgres    false    229    225    3256            �           2606    80414 '   users_roles fk2o0jvgh89lemvvo17cbqvdxaa    FK CONSTRAINT     �   ALTER TABLE ONLY public.users_roles
    ADD CONSTRAINT fk2o0jvgh89lemvvo17cbqvdxaa FOREIGN KEY (user_id) REFERENCES public.users(id);
 Q   ALTER TABLE ONLY public.users_roles DROP CONSTRAINT fk2o0jvgh89lemvvo17cbqvdxaa;
       public          postgres    false    3256    230    229            �           2606    80384 "   ordini fk6odl1p603s8jmass6j0h3bhna    FK CONSTRAINT     �   ALTER TABLE ONLY public.ordini
    ADD CONSTRAINT fk6odl1p603s8jmass6j0h3bhna FOREIGN KEY (user_id) REFERENCES public.users(id);
 L   ALTER TABLE ONLY public.ordini DROP CONSTRAINT fk6odl1p603s8jmass6j0h3bhna;
       public          postgres    false    223    3256    229            �           2606    80399 '   ordini_user fkb3b89okbv7t112lutks7rh2rh    FK CONSTRAINT     �   ALTER TABLE ONLY public.ordini_user
    ADD CONSTRAINT fkb3b89okbv7t112lutks7rh2rh FOREIGN KEY (ordine_id) REFERENCES public.ordini(id);
 Q   ALTER TABLE ONLY public.ordini_user DROP CONSTRAINT fkb3b89okbv7t112lutks7rh2rh;
       public          postgres    false    3244    223    225            �           2606    80369 *   indirizzi_user fkbjlqrth8r1jxic5w1y6y69mw2    FK CONSTRAINT     �   ALTER TABLE ONLY public.indirizzi_user
    ADD CONSTRAINT fkbjlqrth8r1jxic5w1y6y69mw2 FOREIGN KEY (indirizzo_id) REFERENCES public.indirizzi(id);
 T   ALTER TABLE ONLY public.indirizzi_user DROP CONSTRAINT fkbjlqrth8r1jxic5w1y6y69mw2;
       public          postgres    false    220    221    3238            �           2606    80409 '   users_roles fkj6m8fwv7oqv74fcehir1a9ffy    FK CONSTRAINT     �   ALTER TABLE ONLY public.users_roles
    ADD CONSTRAINT fkj6m8fwv7oqv74fcehir1a9ffy FOREIGN KEY (role_id) REFERENCES public.roles(id);
 Q   ALTER TABLE ONLY public.users_roles DROP CONSTRAINT fkj6m8fwv7oqv74fcehir1a9ffy;
       public          postgres    false    230    3248    227            �           2606    80379 "   ordini fknl07kve7ihwehp2dgn8g9f5v4    FK CONSTRAINT     �   ALTER TABLE ONLY public.ordini
    ADD CONSTRAINT fknl07kve7ihwehp2dgn8g9f5v4 FOREIGN KEY (fattura_id) REFERENCES public.fatture(id);
 L   ALTER TABLE ONLY public.ordini DROP CONSTRAINT fknl07kve7ihwehp2dgn8g9f5v4;
       public          postgres    false    3234    223    217            �           2606    80394 4   ordini_articoli_ordinati fkof1wwrf9g0mt3jriw0072qctb    FK CONSTRAINT     �   ALTER TABLE ONLY public.ordini_articoli_ordinati
    ADD CONSTRAINT fkof1wwrf9g0mt3jriw0072qctb FOREIGN KEY (ordini_id) REFERENCES public.ordini(id);
 ^   ALTER TABLE ONLY public.ordini_articoli_ordinati DROP CONSTRAINT fkof1wwrf9g0mt3jriw0072qctb;
       public          postgres    false    224    223    3244            �           2606    80364 %   indirizzi fkplpq215m028r1n3bo6n2hkb2s    FK CONSTRAINT     �   ALTER TABLE ONLY public.indirizzi
    ADD CONSTRAINT fkplpq215m028r1n3bo6n2hkb2s FOREIGN KEY (user_id) REFERENCES public.users(id);
 O   ALTER TABLE ONLY public.indirizzi DROP CONSTRAINT fkplpq215m028r1n3bo6n2hkb2s;
       public          postgres    false    3256    229    220            �           2606    80374 *   indirizzi_user fkrkoi4oidpnfrldsm2e5y5hplk    FK CONSTRAINT     �   ALTER TABLE ONLY public.indirizzi_user
    ADD CONSTRAINT fkrkoi4oidpnfrldsm2e5y5hplk FOREIGN KEY (user_id) REFERENCES public.users(id);
 T   ALTER TABLE ONLY public.indirizzi_user DROP CONSTRAINT fkrkoi4oidpnfrldsm2e5y5hplk;
       public          postgres    false    3256    229    221            �           2606    80354 (   fatture_user fksug81i0h0l8v6bdgvltjamwsi    FK CONSTRAINT     �   ALTER TABLE ONLY public.fatture_user
    ADD CONSTRAINT fksug81i0h0l8v6bdgvltjamwsi FOREIGN KEY (fattura_id) REFERENCES public.fatture(id);
 R   ALTER TABLE ONLY public.fatture_user DROP CONSTRAINT fksug81i0h0l8v6bdgvltjamwsi;
       public          postgres    false    3234    218    217            �           2606    80389 3   ordini_articoli_ordinati fkt9s13dwwn9fmy9rij7o0bx38    FK CONSTRAINT     �   ALTER TABLE ONLY public.ordini_articoli_ordinati
    ADD CONSTRAINT fkt9s13dwwn9fmy9rij7o0bx38 FOREIGN KEY (articoli_ordinati_id) REFERENCES public.articoli(id);
 ]   ALTER TABLE ONLY public.ordini_articoli_ordinati DROP CONSTRAINT fkt9s13dwwn9fmy9rij7o0bx38;
       public          postgres    false    224    3232    215            �           2606    80359 (   fatture_user fktnx6c1pexgribc8a6h7o5o1vc    FK CONSTRAINT     �   ALTER TABLE ONLY public.fatture_user
    ADD CONSTRAINT fktnx6c1pexgribc8a6h7o5o1vc FOREIGN KEY (user_id) REFERENCES public.users(id);
 R   ALTER TABLE ONLY public.fatture_user DROP CONSTRAINT fktnx6c1pexgribc8a6h7o5o1vc;
       public          postgres    false    218    3256    229            Y   �
  x��X�n��>�O�A�݋[")�G'��q��7�������m6�����C��ȼI�$_u����:�`Ö��������0X��*�(���0�(6�Z�J��馒�N�[�H&�{�jvQ�쵑�l�E�Ֆ��Ѭk���V6V�Bow��;iX!�-Tt����Z2+�ZI��f����jp��]�
��,�m%������ތ6���o�sa
��E#�lo�h[i�n�7?tj�۹*�y���*�Q��������=�p�'��p�(�]5�ˮ�v�*ʲe�GI���0��W�W?��n^с_m��6��x�~BҔĳ0Hz��~Ǎ��5�����ȭ��c�����u�Ͻ#$�V�&��HV�Z�7���D&����8��È�'y
�������J��~��*��(<����)7 zz������톽���ˈ���2�k��/v��aw���U�ވ��"�ųhoeQ�b^��
%>� �U�U�q��L�s�,�E<�6�_��V����P���F�+#�M�ǌ��(��0�G0���\���Z㥧u\ �����ǯv��d��1!�@A����Q[���?�}��֬P������AX��&#�7��-��$�X)��az9��b�����%�&񏃅c~��2
{����D`$����ra�VfK�z+�.���AX�-@�R�+�V@%	Vv���d�H-��P�F�;� �Z�b�i��V�v0���[Q��~�~w��8,?bZ���O@�bB���`m-��"�>1S��(���k����{dqH-���@�8r(��
AK��i88[KU�Fu�'8<I�l^��{�p<���8��>ϳ��������lIt`�(�ZR6J��)�K�^�t��d�����00XYa�(����W�N�y�a��ƶ�HK�k��/�E�
�#� ��p��.E�VP<=I�v�s�� ��~�8xu+j
;�\2�S�Ҥ�'�]�J����s�c*@,�)�#����˞���^{W�[�5�,#����`�]Sm�7�QQAHK����` �t�(3EY�>CW�����H�@�*$I�_��E�z�t>%
��X�m����
�,�[g3�{�[ΡzDb5I��Y^H��ċ�*�:Wͳ�E^/0(�%>z�h�Q��Ey����E��O��D��
��c�ґt���5r��q**�Æ� E���i/�40n8���b�G��!|K x	�P;����GS��,	�G����K��,
�p��(
�E��4
\��t$/.Q{�ħf���Մ_���RF�{���.Q!�j�l�P~KE�*#��B�6v�
���>�Ju��H�O��x1�y���x1o�bH4!^?��E�"s�3\�q�UL���>�^���� bF�Fi��-U �L�QTD�p��z���Ֆ6��4ԊPoE �ڷ&'�h�P9�?���o��F^����I�1;�,�i���x��*�jf�� Jb -��*4����m�>ϧ��'�k�h[�B��% $�z�'E���T�ː\��Ra���5�(���j�a��s�®|'�s�.Ռ�I�4L��~�t�!�~7%��Zo�z�v� [���E�V(Γ�����3�<�@	��Dۈ��(�|�9c(b�X#���.�)nP:׊�߃h�M\n�"T�|5Go7�pg,w���Y�hO$�૰2ӠZ��`
�΁���q�t4��Y�Wd���[�����Iqd�EE��(G ��Ds�u�&Z�¸~�F}��k����k����o
;LC\a�T �w���,Y�ɼ��ǂ���|z��o:K7���~�a�@�,u��3?���0:�
Q�ZYRHd�)M����
1s���k�ُN};5�?5�D��V�=�����$)�����<�L�j�����K$����^���+GaS_�U�����7�l����#�;r�3[�;]��gQ��^ݙ'�R�� ��-�߮�v0e2Eq�?k�p�\Yp�Q��@x�«���Bɡs��v�J�WH�Ao�0�<��Ipa>|��>��Q��V����c���Eе;�����ݖv���ݢ��H���/r�`�Aݤ��-��P �>�i8�$Y�^�Z�i�F�51��MT#f��y��'�]�uY�yF�1��̓��B�X�/��F>�i��U����kc|�I�Ps�RC�h\r��i��;M[ՏEeInj��o�	�C\˽�T�A�s�i�F.�y�������5h���d�� D˜X@]����7�;���V���P�B<�Pyx�8�p��'��
�&8��ʹu�^�S�,\ƫ���\8�����װAR�g�{���*����
呝-�E�<��R������{͑�%-���e�p���f��UH�7�ST�6��xj�Ia@������Z��#���5�kA�H0�@��H�T�ya�rU�R��eͽ7�?�b4|%YF�q������-���2���gu��qx����b��B�M2����黯���#o�)��(O�������k�,ҝT۩x���A��o�oxoھG� a<'չ���������)���c�����7]т��ďN��V�}�$�4#3b�@ahL�_�� �A`�u��A�)/�i�Gs�%�d�Qw�x��;�x�_;ӑ=CZLF�/��.�E4��yvvv�_�tV      [      x������ � �      \      x������ � �      ^      x������ � �      _      x������ � �      a      x������ � �      b      x������ � �      c      x������ � �      e   .   x�3���q�wt����2�pB�]���!l_� �� �=... ��      g   o   x�3�LI,�tH�H�-�I�K���tO����tI,�LI�T1JT14P�s��
�/�(��pO7q�/Lq�7���qtqN
�2�(
Nw5-�t�r�4�454#�+F��� � y      h      x�3�4�2�=... ��     