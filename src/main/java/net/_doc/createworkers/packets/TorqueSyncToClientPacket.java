package net._doc.createworkers.packets;

import net._doc.createworkers.capabilities.torque.ITorqueStorageHandler;
import net._doc.createworkers.capabilities.torque.TorqueStorageHandler;
import net._doc.createworkers.entities.Worker;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import team.creative.creativecore.common.network.CreativePacket;

public class TorqueSyncToClientPacket extends CreativePacket {
    public double maxTorque;
    public double currentTorque;
    public int pid;
    
    public TorqueSyncToClientPacket() {}
    
    public TorqueSyncToClientPacket(ITorqueStorageHandler torque, int pid) {
        this.maxTorque = torque.getMaxTorque();
        this.currentTorque = torque.getCurrentTorque();
        this.pid = pid;
    }
    
    @Override
    public void executeClient(Player player) {
        Worker worker = (Worker) player.level().getEntity(pid);
        worker.setTorque(new TorqueStorageHandler(maxTorque, currentTorque));
    }
    
    @Override
    public void executeServer(ServerPlayer player) {
        // TODO Auto-generated method stub
        
    }
    
}
