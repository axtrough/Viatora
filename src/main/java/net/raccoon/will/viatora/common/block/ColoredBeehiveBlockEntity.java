package net.raccoon.will.viatora.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BeehiveBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.raccoon.will.viatora.core.registry.VBlockEntities;
import org.jetbrains.annotations.NotNull;

public class ColoredBeehiveBlockEntity extends BeehiveBlockEntity {

    public ColoredBeehiveBlockEntity(BlockPos pos, BlockState blockState) {
        super(pos, blockState);
    }

    @NotNull
    @Override
    public BlockEntityType<?> getType() {
        return VBlockEntities.BEEHIVE.get();
    }
}
