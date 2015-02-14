//1. Opciones y declarciones
//Se importan los paquetes que se van a utilizar
package ejerciociocupvero;
import java_cup.runtime.Symbol;
import java_cup.runtime.*;
import java.io.Reader;
// Inicio del bloque de configuración
%%
// class.- Esta opción es para que el archivo a
//generar lleve el nombre de AnalizadorLexico
%class AnalizadorLexico
//Cup.- es un analizador sintáctico que permite integrar Jflex y Cup
%cup
//line.- Para el conteo de la línea.
%line



//2. Código de usuario
//Métodos java
%eofval{
	return new Symbol(sym.EOF,new String("Fin del archivo"));
%eofval}


//3. Reglas lexicográficas
//Define la funcionalidad del analizador
//digito.- nos imdica que esta formada por los numeros del 1 a 9
digito = [0-9]
//letra.- contiene el afabaeto mayúscula y minúscula
letra = [a-zA-Z]
//id.- está comprendido por la combinación de letras o dígitos seguidos de un _
id = {letra}({letra}|{digito}|"_")*
//espacio.- se toma a  onsideración espacios y tabulacciones
espacio = \t|\f|" "|\r|\n
//Especcificación de tokens
%%
"/"		{return new Symbol(sym.DIV, yychar, yyline, yytext());}
"="			{return new Symbol(sym.IGUAL, yychar, yyline, yytext());}
"K"			{return new Symbol(sym.KILOMETROS, yychar, yyline, yytext());}
"M"			{return new Symbol(sym.METROS, yychar, yyline, yytext());}
"S"			{return new Symbol(sym.SEGUNDOS, yychar, yyline, yytext());}
"H"			{return new Symbol(sym.HORA, yychar, yyline, yytext());}
{id}		{return new Symbol(sym.ID, yychar, yyline, yytext());}
";"			{return new Symbol(sym.PUNTOYCOMA, yychar, yyline, yytext());}
{digito}*	{return new Symbol(sym.NUMERO, yychar, yyline, new Integer(yytext()));}
{espacio}	{}
.			{ System.out.println("Caracter ilegal: " + yytext()); }
//esta linea nos indica que si no se cumple ninguna de las reglas 
//anteriores el analizador debe de lanzar un mensaje de ERROR
. {return ERROR;}\







