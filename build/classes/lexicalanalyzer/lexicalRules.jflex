package lexicalanalyzer;
import static lexicalanalyzer.Token.*;

%%

%class lexicalRules
%type Token
%char
%line
%column
%ignorecase

whiteSpace=([ \s\t\r] | \r\n | \n | " ")
digits=[0-9]
keywords=("__halt_compiler"|"abstract"|"and"|"array"|"as"|"break"|"callable"|"case"|"catch"|"class"|"clone"|"const"|"continue"|"declare"|"default"|"die"|"do"|"echo"|"else"|"elseif"|"empty"|"enddeclare"|"endfor"|"endforeach"|"endif"|"endswitch"|"endwhile"|"eval"|"exit"|"extends"|"final"|"for"|"foreach"|"function"|"global"|"goto"|"if"|"implements"|"include"|"include_once"|"instanceof"|"insteadof"|"interface"|"isset"|"list"|"namespace"|"new"|"or"|"print"|"private"|"protected"|"public"|"require"|"require_once"|"return"|"static"|"switch"|"throw"|"trait"|"try"|"unset"|"use"|"var"|"while"|"xor")
arithmeticOP=("+"|"-"|"*"|"/"|"%"|"**"|"=")
logicalOP=("and"|"or"|"xor"|"&&"|"||"|"!")
compareOP=("=="|">="|"<="|">"|"<"| "!=")
logicalVal=("true"|"false")
intVal=({digits}+)
realVal=(({digits}*[\.]{digits}+)|({digits}+[\.]{digits}*))
realExpVal=([+-]?(({digits}|{realVal})[eE][+-]?{digits}))
stringVal=(('([^'\n\\]|\\.)*')|(\"([^\"\n\\]|\\.)*\"))
ID=([a-zA-Z_]([a-zA-Z_0-9])*)
variable=("$"{ID})
constants2=("define("{ID}","{stringVal}")")
defaultConstVariables=("__CLASS__"|"__DIR__"|"__FILE__"|"__FUNCTION__"|"__LINE__"|"__METHOD__"|"__NAMESPACE__"|"__TRAIT__")
defaultVariablesUpperCase=("$GLOBALS"|"$_SERVER"|"$_GET"|"$_POST"|"$_FILES"|"$_REQUEST"|"$_SESSION"|"$_ENV"|"$_COOKIE"|"$HTTP_RAW_POST_DATA")
defaultVariablesLowerCase=("$http_response_header"|"$argc"|"$argv"|"$php_errormsg")
symbols=("("|")"|"{"|"}"|";"|":"|"<?php"|"?>"|\.|"["|"]"|",")
function=([a-zA-Z]+"(")
commentsSymbols=(((\#)|(\/\/))[^\r\n]*|\/\*[\s\S]*?\*\/)
accessDataBase=("$recordset["\'{ID}\'"]")
typesVal = ({intVal}|{logicalVal}|{realVal}|{realExpVal}|{stringVal})
typesOfOperators = ({arithmeticOP}|{logicalOP}|{compareOP})
regexInUpperCase = ({defaultConstVariables}|{defaultVariablesUpperCase})
regexInLowerCase = ({keywords}|{defaultVariablesLowerCase}|{logicalVal})



%{
public String lexeme="";
%}

%%
{whiteSpace} {lexeme=yytext(); return WHITESPACE;}
{regexInLowerCase} {lexeme=yytext(); return LOWER_CASE_WORDS;}
{constants2} {lexeme=yytext(); return CONSTANTS_DEFINE;}
{regexInUpperCase} {lexeme=yytext(); return UPPER_CASE_VAR_CONST;}
{accessDataBase} {lexeme=yytext(); return ACCESS_DB;}
{symbols} {lexeme=yytext(); return SYMBOLS;}
{typesVal} {lexeme = yytext(); return TYPES_VAL;}
{commentsSymbols} {lexeme = yytext(); return COMMENTS;}
{variable} {lexeme = yytext(); return VAR;}
{typesOfOperators} {lexeme = yytext(); return TYPES_OP;}
{ID} {lexeme = yytext(); return ID;}
. {lexeme = Integer.toString(yyline + 1); return ERROR;}




