package jflexnetbeans;
import static jflexnetbeans.Token.*;
%%
%class Lexer
%type Token
//%int
//%unicode
// %cup
%line
%column
L=[a-z_]
D=[0-9]
WHITE=[ \t\r\n]
%{
public String lexeme;
%}
%%
{WHITE} {/* ignore */}
"//".* {/* ignore */}
"M" {return METROS;}
"K" {return KILOMETROS;}
"S" {return SEGUNDOS;}
"H" {return HORA;}
"/" {return DIV;}
{L}({L}|{D})* {lexeme=yytext(); return ID;}
[/]?{D}+ {lexeme=yytext();return INT;}
. {return ERROR;}