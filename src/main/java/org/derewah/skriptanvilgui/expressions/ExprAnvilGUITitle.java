package org.derewah.skriptanvilgui.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.util.coll.CollectionUtils;
import org.bukkit.event.Event;
import org.derewah.skriptanvilgui.anvilgui.Anvil;


public class ExprAnvilGUITitle extends SimplePropertyExpression<Anvil, String> {

    static {
        // Skript's own "name/title of %objects%" swallows the plain "title of" patterns, so the
        // "anvil gui" variants are the ones to use on Skript 2.15+. The plain ones are kept for
        // backwards compatibility with older scripts.
        Skript.registerExpression(ExprAnvilGUITitle.class, String.class, ExpressionType.PROPERTY,
                "[the] anvil [gui] title of %virtualanvil%",
                "%virtualanvil%'[s] anvil [gui] title",
                "[the] title of %virtualanvil%",
                "%virtualanvil%'[s] title");
    }

    @Override
    public String convert(Anvil anvil) {
        return anvil.getTitle();
    }

    @Override
    public Class<?>[] acceptChange(final ChangeMode mode){
        if (mode == ChangeMode.SET) {return CollectionUtils.array(String.class);}
        return null;
    }

    @Override
    public void change(Event event, Object[] delta, ChangeMode mode) {
        Anvil anvil = getExpr().getSingle(event);
        if(anvil != null && delta != null){
            anvil.setTitle((String) delta[0]);
        }
    }

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }

    @Override
    protected String getPropertyName() {
        return "title";
    }
}