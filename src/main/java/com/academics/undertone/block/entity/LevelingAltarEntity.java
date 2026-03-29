package com.academics.undertone.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class LevelingAltarEntity extends BlockEntity {

    public LevelingAltarEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.LEVELING_ALTAR_BE.get(), pos, blockState);
    }


}
