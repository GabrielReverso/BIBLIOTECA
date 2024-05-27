CREATE DATABASE BIBLIOTECA;

/*ENTRAR NO BANCO BIBLIOTECA ANTES*/

/*TABELAS*/
create table tb_usuario (
	id serial primary key,
	nome varchar(64),
	email varchar(100),
	senha varchar(32),
	possuiLivro boolean
);

create table tb_livro (
	id serial primary key,
	titulo varchar(64),
	autor varchar(64),
	editora varchar(64),
	descricao text,
	imagePath text
);

create table tb_acervo (
	id serial primary key,
	localidade varchar(100)
);

/*INSERÇÕES PRÉVIAS*/
INSERT INTO tb_livro (titulo, autor, editora, descricao) VALUES
	('Inteligência Artificial: Uma Abordagem Prática', 'John Doe', 'Editora XYZ', 'Este livro oferece uma visão prática da inteligência artificial, abordando desde conceitos básicos até aplicações avançadas.'),
	('Programação Orientada a Objetos em Java', 'Jane Smith', 'Editora ABC', 'Uma introdução completa à programação orientada a objetos usando Java, ideal para iniciantes.'),
	('Design de Software: Fundamentos e Técnicas', 'Robert Johnson', 'Editora DEF', 'Explora os fundamentos do design de software e apresenta técnicas modernas para criar sistemas eficientes.'),
	('Engenharia de Requisitos: Uma Abordagem Ágil', 'Mary Williams', 'Editora GHI', 'Focado na engenharia de requisitos, este livro explora métodos ágeis para coleta e análise de requisitos.'),
	('Científica da Computação: Teoria e Aplicação', 'David Brown', 'Editora JKL', 'Aborda a ciência da computação como uma disciplina interdisciplinar, explorando teorias e suas aplicações.'),
	('Segurança Cibernética: Protegendo Sistemas Digitais', 'Emily Davis', 'Editora MNO', 'Um guia detalhado sobre segurança cibernética, cobrindo ameaças, defesas e melhores práticas.'),
	('Análise de Algoritmos: Estratégias Eficientes', 'Michael Green', 'Editora PQR', 'Descreve estratégias eficientes para analisar algoritmos, ajudando os leitores a entender e otimizar seu desempenho.'),
	('Princípios de Arquitetura de Software', 'Sarah Taylor', 'Editora STU', 'Explore os princípios fundamentais da arquitetura de software, incluindo padrões de projeto e design.'),
	('Gestão de Projetos de Software: Ferramentas e Técnicas', 'James White', 'Editora VWX', 'Oferece uma visão abrangente da gestão de projetos de software, incluindo ferramentas e técnicas essenciais.'),
	('Tecnologias Web Modernas: Desenvolvimento Front-end e Back-end', 'Linda Black', 'Editora YZA', 'Cobre tecnologias web modernas, focando em desenvolvimento front-end e back-end, para construir aplicativos web robustos.');

insert  into tb_acervo (localidade) values ('Ribeirão Preto'), ('São Paulo');

/*TABELAS RELACIONAIS*/
create table tb_livro_usuario (
	id_usuario int references tb_usuario(id) on delete cascade,
	id_livro int references tb_livro(id) on delete cascade,
	dataEmprestimo date,
	prazo date,
	expirado boolean
);

create table tb_livro_acervo (
	id_livro int references tb_livro(id) on delete cascade,
	id_acervo int references tb_acervo(id) on delete cascade,
	quantidade int,
	disponibilidade int
);

/*SCRIPT POSTGRE PARA ADICIONAR TODOS OS LIVROS NOS DOIS ACERVOS AUTOMATICAMENTE*/
do $$
declare
	livro_id integer;
	acervo_id integer;
begin
	for livro_id in select id from tb_livro loop
		for acervo_id in select id from tb_acervo loop
			insert into tb_livro_acervo (id_livro, id_acervo, quantidade, disponibilidade)
			values (livro_id, acervo_id, 3, 3);			
		end loop;
	end loop;
end $$;

select *
FROM tb_livro tl 
INNER JOIN tb_livro_acervo tla ON tl.id = tla.id_livro 
INNER JOIN tb_acervo ta ON ta.id = tla.id_acervo  
WHERE ta.localidade = 'Ribeirão Preto';

select *
FROM tb_livro_acervo tla 
INNER JOIN tb_acervo ta ON ta.id = tla.id_acervo  
WHERE ta.localidade = 'Ribeirão Preto';

select *
from tb_livro tl 
inner join tb_livro_acervo tla on tl.id = tla.id_livro 
INNER JOIN tb_acervo ta ON ta.id = tla.id_acervo 
where ta.localidade = 'Ribeirão Preto' and tl.titulo ilike '%Técnicas%';

select disponibilidade from tb_livro_acervo tla  
inner join tb_acervo ta on tla.id_acervo = ta.id 
where tla.id_livro = 1 and ta.localidade = 'Ribeirão Preto';


