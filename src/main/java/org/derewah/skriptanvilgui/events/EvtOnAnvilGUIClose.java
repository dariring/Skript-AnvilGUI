package org.derewah.skriptanvilgui.events;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.util.SimpleEvent;
import ch.njol.skript.registrations.EventValues;
import org.bukkit.entity.Player;
import org.derewah.skriptanvilgui.anvilgui.Anvil;
import org.derewah.skriptanvilgui.events.Anvil.BridgeAnvilClose;

public class EvtOnAnvilGUIClose {

    static{
        Skript.registerEvent("On AnvilGUI Close", SimpleEvent.class, BridgeAnvilClose.class, "anvil gui close");
        EventValues.registerEventValue(BridgeAnvilClose.class, String.class,
                bridgeAnvilClose -> bridgeAnvilClose.getAnvilState().getText(), EventValues.TIME_NOW);
        EventValues.registerEventValue(BridgeAnvilClose.class, Player.class,
                bridgeAnvilClose -> bridgeAnvilClose.getAnvilState().getPlayer(), EventValues.TIME_NOW);
        EventValues.registerEventValue(BridgeAnvilClose.class, Anvil.class,
                BridgeAnvilClose::getAnvil, EventValues.TIME_NOW);
    }

}
