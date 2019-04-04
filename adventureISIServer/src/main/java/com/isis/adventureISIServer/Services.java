/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isis.adventureISIServer;

import generated.World;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author asanto01
 */
public class Services {

  

    /*Désérialisation du XML*/
    public World readWorldFromXml() {
        JAXBContext jc;
        World world;
        try {
            InputStream input = getClass().getClassLoader().getResourceAsStream("XMLmonde.xml");
            jc = JAXBContext.newInstance(World.class);
            Unmarshaller u = jc.createUnmarshaller();
            world = (World) u.unmarshal(input);
            return world;
        } catch (JAXBException ex) {
            Logger.getLogger(Services.class.getName()).log(Level.SEVERE, null, ex);
        }        
        return null;
    }

    /*Sérialisation*/
    public void saveWorldToXml(World world){
        try {
            OutputStream output = new FileOutputStream("world.xml");
            JAXBContext cont = JAXBContext.newInstance(World.class);
            Marshaller m = cont.createMarshaller();
            m.marshal(m, output);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Services.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JAXBException ex) {
            Logger.getLogger(Services.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public World getWorld() {
        World world = readWorldFromXml();
        return world;
    }
}
