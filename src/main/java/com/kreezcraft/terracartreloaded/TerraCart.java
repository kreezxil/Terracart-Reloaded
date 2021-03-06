package com.kreezcraft.terracartreloaded;

import org.apache.logging.log4j.Logger;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

import java.io.File;

@Mod(modid = TerraCart.MODID, name = TerraCart.MODNAME, version = TerraCart.VERSION)
public class TerraCart {
    public static final String MODID = "terracart";
    public static final String MODNAME = "TerraCart";
    public static final String VERSION = "@VERSION@";
    public static Logger logger;
    public static Configuration config;
    public static File configFile,directory; 
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent e) {
        TerraCart.logger = e.getModLog();
        directory = e.getModConfigurationDirectory();
        configFile = new File(directory.getPath(), TerraCart.MODID+".cfg"); 
        config = new Configuration(configFile);
        Config.readConfig();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent e) {
        MinecraftForge.EVENT_BUS.register(new RailsClickHandler());
        MinecraftForge.EVENT_BUS.register(new DismountHandler());
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent e) {
        if (config.hasChanged()) {
            config.save();
        }
    }
    @Mod.EventHandler
	public void serverLoad(FMLServerStartingEvent event) {
		event.registerServerCommand(new CommandTC());
    }
}
