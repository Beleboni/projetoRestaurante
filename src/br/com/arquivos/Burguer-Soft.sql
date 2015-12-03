 -- drop table funcionario;

create table funcionario (
	codigo int not null primary key auto_increment,
    nome varchar(100) not null,
    cpf varchar(20) not null,
    telefone varchar(15) not null,
    setor varchar(30) not null,
    admissao date not null,
    usuario varchar(40) not null unique key, 
    senha varchar(40) not null,
    status varchar(30) not null
);

-- drop table produto;

create table produto (

	codigo int not null primary key auto_increment,
    tipoProduto varchar(100) not null,
    valor double not null,
    descricao varchar (300) not null,
    status varchar(30) not null
);

-- drop table itempedido;

create table itempedido (
	codigoItemPedido int not null primary key auto_increment,
	codigoProduto int,
    codigoPedido int,
	foreign key (codigoProduto) references produto(codigo),
	foreign key (codigoPedido) references pedido(codigoPedido),
    status varchar(30) not null
);

-- drop table pedido;

create table pedido (
	codigoPedido int not null primary key auto_increment,
    mesa int not null,
    status varchar(30) not null,
    dataPedido date not null,
    total double not null
    
);

-- Alter table itempedido Add status varchar(30) not null;
-- drop table statusPedido;
-- alter table pedido drop column statusItem; 


-- delete from produto;
-- delete from itempedido;
-- delete from pedido;

-- insert into itempedido values(1, 1, 1);

-- insert into pedido values(1, 01, 1);

 alter table pedido add total double not null;

select p.codigoPedido
	from pedido p where mesa = 4;

select * from pedido where mesa = 1;

select * from pedido;


select * from produto p 
	join itempedido ip on p.codigo = ip.codigoProduto 
		join pedido pe on ip.codigoPedido = pe.codigoPedido 
			where mesa = 1 group by p.descricao;

select p.codigo, p.descricao, pe.codigoPedido
	from produto p
		join itempedido ip on p.codigo = ip.codigoProduto 
		join pedido pe on ip.codigoPedido = pe.codigoPedido 
			where mesa = 1 group by p.descricao;
            
SELECT * FROM produto where tipoProduto = 'RODIZIOPIZZA' and status = 'ATIVO';

select * from pedido where status = 'PROCESSANDO';

select * from itempedido;


SELECT restaurante_v1.pedido.`codigoPedido`,
	restaurante_v1.pedido.mesa,
	restaurante_v1.pedido.status,
	restaurante_v1.pedido.`dataPedido`,
	restaurante_v1.pedido.total
FROM restaurante_v1.pedido where restaurante_v1.pedido.status = 'CONCLUIDO';


SELECT restaurante_v1.produto.codigo,
	restaurante_v1.produto.`tipoProduto`,
	restaurante_v1.produto.valor,
	restaurante_v1.produto.descricao,
	restaurante_v1.produto.status,
	restaurante_v1.itempedido.`codigoItemPedido`,
	restaurante_v1.itempedido.`codigoProduto`,
	restaurante_v1.itempedido.`codigoPedido`,
	restaurante_v1.itempedido.status,
	restaurante_v1.pedido.`codigoPedido`,
	restaurante_v1.pedido.mesa,
	restaurante_v1.pedido.total,
	restaurante_v1.pedido.`dataPedido`,
	restaurante_v1.pedido.status
FROM restaurante_v1.itempedido
	INNER JOIN restaurante_v1.produto ON 
	 restaurante_v1.itempedido.`codigoProduto` = restaurante_v1.produto.codigo 
	INNER JOIN restaurante_v1.pedido ON  
	 restaurante_v1.itempedido.`codigoPedido` = restaurante_v1.pedido.`codigoPedido`  where restaurante_v1.pedido.status = 'PROCESSANDO';


select * from pedido where status <> 'CONCLUIDO' and mesa = 3;
