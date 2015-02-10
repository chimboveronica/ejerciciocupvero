package ejerciociocupvero;
import java_cup.runtime.Symbol;
import java_cup.runtime.*;
import java.io.Reader;

%%
%class AnalizadorLexico
%cup
%full
%line
%char
%ignorecase
%eofval{
	return new Symbol(sym.EOF,new String("Fin del archivo"));
%eofval}

digito = [0-9]
letra = [a-zA-Z]
id = {letra}({letra}|{digito}|"_")*
espacio = \t|\f|" "|\r|\n

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
. {return ERROR;}