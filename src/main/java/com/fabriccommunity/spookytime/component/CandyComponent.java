package com.fabriccommunity.spookytime.component;

import nerdhub.cardinal.components.api.component.Component;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

public interface CandyComponent extends Component {
	public boolean canGiveCandy(Entity entity);
	
	public void setLastCandyTime(Entity entity, long time);
	
	public class VillagerCandyComponent implements CandyComponent {
		public Map<UUID, Long> lastGivenCandy = new HashMap<UUID, Long>();
		
		public boolean canGiveCandy(Entity entity) {
			if (!lastGivenCandy.containsKey(entity.getUuid())) {
				return true;
			} else {
				return lastGivenCandy.get(entity.getUuid()) + 24000L < entity.getEntityWorld().getTime();
			}
		}
		
		public void setLastCandyTime(Entity entity, long time) {
			lastGivenCandy.put(entity.getUuid(), time);
		}
		
		@Override
		public void fromTag(CompoundTag tag) {
			ListTag list = tag.getList("entities", 10);
			for (int i = 0; i < list.size(); i++) {
				CompoundTag item = list.getCompoundTag(i);
				lastGivenCandy.put(item.getUuid("uuid"), item.getLong("time"));
			}
		}
		
		@Override
		public CompoundTag toTag(CompoundTag tag) {
			ListTag list = new ListTag();
			Iterator<Map.Entry<UUID, Long>> iterator = lastGivenCandy.entrySet().iterator();
			while (iterator.hasNext()) {
				Map.Entry<UUID, Long> entry = iterator.next();
				CompoundTag item = new CompoundTag();
				item.putUuid("uuid", entry.getKey());
				item.putLong("time", entry.getValue());
				list.add(item);
			}
			tag.put("entities", list);
			return tag;
		}
	}
}