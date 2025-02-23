PGDMP                      |            tourismagency    16.2    16.2 &    �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    16991    tourismagency    DATABASE     �   CREATE DATABASE tourismagency WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'English_United States.1254';
    DROP DATABASE tourismagency;
                postgres    false            �            1259    16992    hotel    TABLE     �  CREATE TABLE public.hotel (
    id integer NOT NULL,
    name text NOT NULL,
    city text NOT NULL,
    region text NOT NULL,
    address text NOT NULL,
    email text,
    phone text NOT NULL,
    star text NOT NULL,
    car_park boolean NOT NULL,
    wifi boolean NOT NULL,
    pool boolean NOT NULL,
    fitness boolean NOT NULL,
    concierge boolean NOT NULL,
    spa boolean NOT NULL,
    room_service boolean NOT NULL
);
    DROP TABLE public.hotel;
       public         heap    postgres    false            �            1259    16995    Hotel_id_seq    SEQUENCE     �   ALTER TABLE public.hotel ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public."Hotel_id_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    215            �            1259    17029    pension_type    TABLE     }   CREATE TABLE public.pension_type (
    id integer NOT NULL,
    hotel_id integer NOT NULL,
    pension_type text NOT NULL
);
     DROP TABLE public.pension_type;
       public         heap    postgres    false            �            1259    17038    pension_type_id_seq    SEQUENCE     �   ALTER TABLE public.pension_type ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.pension_type_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    224            �            1259    17026    reservation    TABLE     X  CREATE TABLE public.reservation (
    id integer NOT NULL,
    room_id integer NOT NULL,
    check_in_date date NOT NULL,
    check_out_date date NOT NULL,
    total_price integer NOT NULL,
    guest_count integer NOT NULL,
    guest_name text NOT NULL,
    guest_citizen_id text NOT NULL,
    guest_mail text,
    guest_phone text NOT NULL
);
    DROP TABLE public.reservation;
       public         heap    postgres    false            �            1259    17032    reservation_id_seq    SEQUENCE     �   ALTER TABLE public.reservation ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.reservation_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    223            �            1259    17017    room    TABLE     �  CREATE TABLE public.room (
    id integer NOT NULL,
    hotel_id integer NOT NULL,
    pension_id integer NOT NULL,
    season_id integer NOT NULL,
    type text NOT NULL,
    stock integer NOT NULL,
    adult_price integer NOT NULL,
    child_price integer NOT NULL,
    bed_capacity integer NOT NULL,
    square_meter integer NOT NULL,
    television boolean NOT NULL,
    minibar boolean NOT NULL,
    game_console boolean NOT NULL,
    cash_box boolean NOT NULL,
    projection boolean NOT NULL
);
    DROP TABLE public.room;
       public         heap    postgres    false            �            1259    17020    room_id_seq    SEQUENCE     �   ALTER TABLE public.room ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.room_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    221            �            1259    17008    season    TABLE     �   CREATE TABLE public.season (
    id integer NOT NULL,
    hotel_id integer NOT NULL,
    start_date date NOT NULL,
    finish_date date NOT NULL
);
    DROP TABLE public.season;
       public         heap    postgres    false            �            1259    17011    season_id_seq    SEQUENCE     �   ALTER TABLE public.season ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.season_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    219            �            1259    16999    user    TABLE     �   CREATE TABLE public."user" (
    id integer NOT NULL,
    username text NOT NULL,
    password text NOT NULL,
    role text NOT NULL
);
    DROP TABLE public."user";
       public         heap    postgres    false            �            1259    17002    user_id_seq    SEQUENCE     �   ALTER TABLE public."user" ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    217            �          0    16992    hotel 
   TABLE DATA           �   COPY public.hotel (id, name, city, region, address, email, phone, star, car_park, wifi, pool, fitness, concierge, spa, room_service) FROM stdin;
    public          postgres    false    215   �-       �          0    17029    pension_type 
   TABLE DATA           B   COPY public.pension_type (id, hotel_id, pension_type) FROM stdin;
    public          postgres    false    224   6/       �          0    17026    reservation 
   TABLE DATA           �   COPY public.reservation (id, room_id, check_in_date, check_out_date, total_price, guest_count, guest_name, guest_citizen_id, guest_mail, guest_phone) FROM stdin;
    public          postgres    false    223   �/       �          0    17017    room 
   TABLE DATA           �   COPY public.room (id, hotel_id, pension_id, season_id, type, stock, adult_price, child_price, bed_capacity, square_meter, television, minibar, game_console, cash_box, projection) FROM stdin;
    public          postgres    false    221   �0       �          0    17008    season 
   TABLE DATA           G   COPY public.season (id, hotel_id, start_date, finish_date) FROM stdin;
    public          postgres    false    219   �1       �          0    16999    user 
   TABLE DATA           >   COPY public."user" (id, username, password, role) FROM stdin;
    public          postgres    false    217   �1       �           0    0    Hotel_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public."Hotel_id_seq"', 17, true);
          public          postgres    false    216            �           0    0    pension_type_id_seq    SEQUENCE SET     B   SELECT pg_catalog.setval('public.pension_type_id_seq', 22, true);
          public          postgres    false    226            �           0    0    reservation_id_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('public.reservation_id_seq', 26, true);
          public          postgres    false    225            �           0    0    room_id_seq    SEQUENCE SET     :   SELECT pg_catalog.setval('public.room_id_seq', 24, true);
          public          postgres    false    222            �           0    0    season_id_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('public.season_id_seq', 18, true);
          public          postgres    false    220            �           0    0    user_id_seq    SEQUENCE SET     :   SELECT pg_catalog.setval('public.user_id_seq', 15, true);
          public          postgres    false    218            4           2606    17045    hotel hotel_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.hotel
    ADD CONSTRAINT hotel_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.hotel DROP CONSTRAINT hotel_pkey;
       public            postgres    false    215            >           2606    17047    pension_type pension_type_pkey 
   CONSTRAINT     \   ALTER TABLE ONLY public.pension_type
    ADD CONSTRAINT pension_type_pkey PRIMARY KEY (id);
 H   ALTER TABLE ONLY public.pension_type DROP CONSTRAINT pension_type_pkey;
       public            postgres    false    224            <           2606    17049    reservation reservation_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.reservation
    ADD CONSTRAINT reservation_pkey PRIMARY KEY (id);
 F   ALTER TABLE ONLY public.reservation DROP CONSTRAINT reservation_pkey;
       public            postgres    false    223            :           2606    17051    room room_pkey 
   CONSTRAINT     L   ALTER TABLE ONLY public.room
    ADD CONSTRAINT room_pkey PRIMARY KEY (id);
 8   ALTER TABLE ONLY public.room DROP CONSTRAINT room_pkey;
       public            postgres    false    221            8           2606    17053    season season_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY public.season
    ADD CONSTRAINT season_pkey PRIMARY KEY (id);
 <   ALTER TABLE ONLY public.season DROP CONSTRAINT season_pkey;
       public            postgres    false    219            6           2606    17055    user user_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public."user"
    ADD CONSTRAINT user_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public."user" DROP CONSTRAINT user_pkey;
       public            postgres    false    217            B           2606    17066    pension_type fk_hotel_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.pension_type
    ADD CONSTRAINT fk_hotel_id FOREIGN KEY (hotel_id) REFERENCES public.hotel(id) ON DELETE CASCADE;
 B   ALTER TABLE ONLY public.pension_type DROP CONSTRAINT fk_hotel_id;
       public          postgres    false    215    4660    224            @           2606    17086    room fk_hotel_r_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.room
    ADD CONSTRAINT fk_hotel_r_id FOREIGN KEY (hotel_id) REFERENCES public.hotel(id) ON DELETE CASCADE;
 <   ALTER TABLE ONLY public.room DROP CONSTRAINT fk_hotel_r_id;
       public          postgres    false    4660    221    215            ?           2606    17081    season fk_hotel_s_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.season
    ADD CONSTRAINT fk_hotel_s_id FOREIGN KEY (hotel_id) REFERENCES public.hotel(id) ON DELETE CASCADE;
 >   ALTER TABLE ONLY public.season DROP CONSTRAINT fk_hotel_s_id;
       public          postgres    false    219    4660    215            A           2606    17096    reservation fk_room_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.reservation
    ADD CONSTRAINT fk_room_id FOREIGN KEY (room_id) REFERENCES public.room(id) ON DELETE CASCADE;
 @   ALTER TABLE ONLY public.reservation DROP CONSTRAINT fk_room_id;
       public          postgres    false    4666    221    223            �   �  x����n�0�ϓ���I��,T�j�J�֋K&�Jj#��/�o�@�{k�^5!eIQW{<3�����å�s���>�O':�ՆR��(�[�d�L�/@{X�,�i��T�JC9�k74;:s� �0�(
�'\H�f�)�0H� ������o1��H��2��<������o���k6.9X��tbz�ŋ���>*��E'����j�F|�#0���r[�o��͸��)�7��^6;��u���V�C�1Šq���V�s������ߦ�Z�;��Nj���6�C0��\��5K3�[��k��	�)%ql+7���j���+2�5����=Ϣ(N0I�8��ᑒ�U��Q3ⷺ�>�Ρa����p�h���΁t6p��Nm}7��$ɝhЇkzj�=�,Ϣ^�Y�w��׻��yo`'#      �   �   x����
1E뙏���E�D���ɺ�1�<��7�icιWt���2�х�c@��`��Z���!N�����������@��K2�gڇfg�p(%��OM����t4��ӗ�N1�I'gn��Ţ~���5���e��o�RB^      �   �   x����N�0���S�:�N\�L+��� q,BH͊4���8�4ɇĊ���F��I=D� ����㑀8Dio�!�i����t�w_��c̀^D�4M���+���2��ތ�;���Gtps� :�J�P(]u(D]�+	�_�^�����I1�>RNs�:���J'�q�f�`7-�XZ_ ��Rg�WSKԵO�X����}ᜉ:���ؽ��s�7l>      �   �   x�U��
�0Dϓ��$�ml{���^A����w�@#��f�L�-�UH�1ί��x�l7�ӓr"[�i}��w�b�!�tY瘾�q��d�Jjy�FnY�.��B����͟��Lu��,k�s�!�Bn)B���������C���@d�~#�����1I?�      �   I   x�u���0kj�'�d�9b�AC��#7O��ƍ�B|��+J���ٽ%fs�����"�D� Q      �   X   x�34��J�N�442�t���tu�2�t�OB2��M,�D3�t��LF�k�闙�st����2�����CUd�阒����*F��� P!     