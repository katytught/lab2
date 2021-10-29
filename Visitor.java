import java.util.Locale;
import java.util.Objects;

public class Visitor extends calcBaseVisitor<Void>{
    public String results="";
    public int Num=0;
    static Integer getnumber(String s){
        int res = 0;
        s = s.toLowerCase(Locale.ROOT);
        if (s.charAt(0)=='0'){
            if(s.charAt(1)=='x'||s.charAt(1)=='X'){
                int len = s.length();
                s = s.toLowerCase();
                for (int i=2;i<len;i++){
                    if(s.charAt(i)>='0'&&s.charAt(i)<='9'){
                        res=16*res+ (int) s.charAt(i)-48;
                    }
                    else if(s.charAt(i)>='a'&&s.charAt(i)<='f'){
                        res=16*res +10+ ((int) s.charAt(i)-'a');
                    }
                    else {
                        return res;
                    }
                }
                return res;
            }
            else {
                int len = s.length();
                for(int i=1;i<len;i++){
                    res=8*res+ (int) s.charAt(i)-48;
                }
                return res;
            }
        }
        else if(s.charAt(0)<'0'||s.charAt(0)>'9'){
            return null;
        }
        else {
            return Integer.valueOf(s);
        }
    }
    @Override public Void visitCompUnit(calcParser.CompUnitContext ctx) {
        return super.visitCompUnit(ctx);
    }
    @Override public Void visitFuncDef(calcParser.FuncDefContext ctx) {
        if(ctx.FuncType().getText().equals("int")){
            results+="define dso_local ";
        }
        if(ctx.Ident().getText().equals("main")){
            results+="i32 @main";
        }
        results+="()";
        visit(ctx.block());
        return null;
    }
    @Override public Void visitBlock(calcParser.BlockContext ctx) {
        results+="{\n";
        visit(ctx.stmt());
        results+="}";
        return null;
    }
    @Override public Void visitStmt(calcParser.StmtContext ctx) {
        visit(ctx.exp());
        results+="ret i32 %x"+(Num-1)+"\n";
        System.out.println("ret i32 %x"+(Num-1));
        return null;
    }

    @Override
    public String visitExp(calcParser.ExpContext ctx) {
        return visitAddexp(ctx.addexp());
    }

    @Override
    public String visitAddexp(calcParser.AddexpContext ctx) {
        switch (ctx.children.size()){
            case 1:
                return visitMulexp(ctx.mulexp());
            case 3:
                String left=visitAddexp(ctx.addexp());
                String right=visitMulexp(ctx.mulexp());
                if(Objects.equals(ctx.Addfunc().getText(), "+")){
                    results+="%x"+Num+" = add i32 "+left+","+right+"\n";
                    System.out.println("%x"+Num+" = add i32 "+left+","+right);
                    Num++;
                    return "%x"+(Num-1);
//                    if(!Character.isLetterOrDigit(ctx.addexp().getText().charAt(ctx.addexp().getText().length()-1))){
//                        System.out.print("0, ");
//                    }
//                    else {
//                        System.out.print(ctx.addexp().getText().charAt(ctx.addexp().getText().length()-1)+",");
//                    }
//                    if(!Character.isLetterOrDigit(ctx.mulexp().getText().charAt(0))){
//                        System.out.println("%x"+(Num-2));
//                    }
//                    else {
//                        System.out.println(ctx.mulexp().getText().charAt(0));
//                    }
                }
                else if(Objects.equals(ctx.Addfunc().getText(), "-")){
                    results+="%x"+Num+" = sub i32 "+left+","+right+"\n";
                    System.out.print("%x"+Num+" = sub i32 "+left+","+right);
                    Num++;
                    return "%x"+(Num-1);
//                    if(!Character.isLetterOrDigit(ctx.addexp().getText().charAt(ctx.addexp().getText().length()-1))){
//                        System.out.print("0, ");
//                    }
//                    else {
//                        System.out.print(ctx.addexp().getText().charAt(ctx.addexp().getText().length()-1)+",");
//                    }
//                    if(!Character.isLetterOrDigit(ctx.mulexp().getText().charAt(0))){
//                        System.out.println("%x"+(Num-2));
//                    }
//                    else {
//                        System.out.println(ctx.mulexp().getText().charAt(0));
//                    }
                }
                else {
                    System.out.println("not found");
                }
//                visit(ctx.mulexp());
                break;
        }
        return null;
    }

    @Override
    public String visitMulexp(calcParser.MulexpContext ctx) {
        switch (ctx.children.size()){
            case 1:
                return visitUnaryexp(ctx.unaryexp());
            case 3:
                String left=visitMulexp(ctx.mulexp());
                String right=visitUnaryexp(ctx.unaryexp());
                if(ctx.Mulfunc().getText().equals("*")){
                    results+="%x"+Num+" = mul i32 "+left+","+right+"\n";
                    System.out.println("%x"+Num+" = mul i32 "+left+","+right);
                    Num++;
                    return "%x"+(Num-1);
//                    if(!Character.isLetterOrDigit(ctx.mulexp().getText().charAt(ctx.mulexp().getText().length()-1
//                    ))&&!Character.isLetterOrDigit(ctx.unaryexp().getText().charAt(0))){
//                        System.out.println("%x"+Num+" = mul i32 %x"+(Num-2)+", %x"+(Num-1));
//                        Num++;
//                    }
//                    else if(!Character.isLetterOrDigit(ctx.mulexp().getText().charAt(ctx.mulexp().getText().length()-1
//                    ))){
//                        System.out.println("%x"+Num+" = mul i32 %x"+(Num-1)+", "+ctx.unaryexp().getText().charAt(0));
//                        Num++;
//                    }
//                    else if(!Character.isLetterOrDigit(ctx.unaryexp().getText().charAt(0))){
//                        System.out.println("%x"+Num+" = mul i32 "+ctx.mulexp().getText().charAt(ctx.mulexp().getText().length()-1
//                        )+", %x"+(Num-1));
//                        Num++;
//                    }
                }
                else if(ctx.Mulfunc().getText().equals("/")) {
                    results+="%x" + Num + " = sdiv i32 " + left + "," + right+"\n";
                    System.out.println("%x" + Num + " = sdiv i32 " + left + "," + right);
                    Num++;
                    return "%x" + (Num - 1);
                }
                else if(ctx.Mulfunc().getText().equals("%")) {
                    results+="%x" + Num + " = srem i32 " + left + "," + right+"\n";
                    System.out.println("%x" + Num + " = srem i32 " + left + "," + right);
                    Num++;
                    return "%x" + (Num - 1);
                }
//                visit(ctx.unaryexp());
                break;
        }
        return null;
    }

    @Override
    public String visitUnaryexp(calcParser.UnaryexpContext ctx) {
        switch (ctx.children.size()){
            case 1:
                return visitPrimaryexp(ctx.primaryexp());
            case 2:
                String right=visitUnaryexp(ctx.unaryexp());
                if(ctx.Addfunc().getText().equals("+")){
                    results+="%x"+Num+" = add i32 0, "+right+"\n";
                    System.out.println("%x"+Num+" = add i32 0, "+right);
                    Num++;
                    return "%x"+(Num-1);
                }
                else if(ctx.Addfunc().getText().equals("-")){
                    results+="%x"+Num+" = sub i32 0, "+right+"\n";
                    System.out.println("%x"+Num+" = sub i32 0, "+right);
                    Num++;
                    return "%x"+(Num-1);
                }
//                if(!Character.isLetterOrDigit(ctx.unaryexp().getText().charAt(0))){
//                    System.out.println("%x"+(Num-2));
//                }
//                else {
//                    System.out.println(getnumber(ctx.unaryexp().getText()));
//                }
//                visit(ctx.unaryexp());
                break;
        }
        return null;
    }

    @Override
    public String visitPrimaryexp(calcParser.PrimaryexpContext ctx) {
        switch (ctx.children.size()){
            case 1:
                String s = ctx.Number().getText();
                int temp=getnumber(s);
                //results+=temp;
                return String.valueOf(temp);
            case 3:
//                results+="(";
//                results+=")";
                return visitExp(ctx.exp());
        }
        return null;
    }
}
