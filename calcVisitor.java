// Generated from .\calc.g4 by ANTLR 4.9.2
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link calcParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface calcVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link calcParser#compUnit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompUnit(calcParser.CompUnitContext ctx);
	/**
	 * Visit a parse tree produced by {@link calcParser#funcDef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncDef(calcParser.FuncDefContext ctx);
	/**
	 * Visit a parse tree produced by {@link calcParser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(calcParser.BlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link calcParser#stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmt(calcParser.StmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link calcParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	String visitExp(calcParser.ExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link calcParser#addexp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	String visitAddexp(calcParser.AddexpContext ctx);
	/**
	 * Visit a parse tree produced by {@link calcParser#mulexp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	String visitMulexp(calcParser.MulexpContext ctx);
	/**
	 * Visit a parse tree produced by {@link calcParser#unaryexp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	String visitUnaryexp(calcParser.UnaryexpContext ctx);
	/**
	 * Visit a parse tree produced by {@link calcParser#primaryexp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	String visitPrimaryexp(calcParser.PrimaryexpContext ctx);
}