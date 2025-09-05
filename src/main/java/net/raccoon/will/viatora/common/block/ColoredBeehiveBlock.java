package net.raccoon.will.viatora.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BeehiveBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.raccoon.will.viatora.core.registry.VBlockEntities;
import net.raccoon.will.viatora.core.registry.VBlocks;
import org.jetbrains.annotations.Nullable;

public class ColoredBeehiveBlock extends BeehiveBlock {

    private final DyeColor color;

    public ColoredBeehiveBlock(DyeColor color, Properties properties) {
        super(properties);
        this.color = color;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return VBlockEntities.BEEHIVE.get().create(pos, state);
    }

    public static Block getBlockbyColor (DyeColor color) {
        if (color == null) {
            return Blocks.BEEHIVE;
        } else {
            Block result;
            switch (color) {
                case RED -> result = VBlocks.RED_BEEHIVE.get();
                case ORANGE -> result = VBlocks.ORANGE_BEEHIVE.get();
                case YELLOW -> result = VBlocks.YELLOW_BEEHIVE.get();
                case LIME -> result = VBlocks.LIME_BEEHIVE.get();
                case GREEN -> result = VBlocks.GREEN_BEEHIVE.get();
                case CYAN -> result = VBlocks.CYAN_BEEHIVE.get();
                case LIGHT_BLUE -> result = VBlocks.LIGHT_BLUE_BEEHIVE.get();
                case BLUE -> result = VBlocks.BLUE_BEEHIVE.get();
                case PURPLE -> result = VBlocks.PURPLE_BEEHIVE.get();
                case MAGENTA -> result = VBlocks.MAGENTA_BEEHIVE.get();
                case PINK -> result = VBlocks.PINK_BEEHIVE.get();
                case WHITE -> result = VBlocks.WHITE_BEEHIVE.get();
                case LIGHT_GRAY -> result = VBlocks.LIGHT_GRAY_BEEHIVE.get();
                case GRAY -> result = VBlocks.GRAY_BEEHIVE.get();
                case BLACK -> result = VBlocks.BLACK_BEEHIVE.get();
                case BROWN -> result = VBlocks.BROWN_BEEHIVE.get();
                default -> throw new MatchException((String)null, (Throwable)null);
            }
            return result;
        }
    }


    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        return level.isClientSide ? null : createTickerHelper(type, VBlockEntities.BEEHIVE.get(), BeehiveBlockEntity::serverTick);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rot) {
        return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirrorIn) {
        return mirrorIn == Mirror.NONE ? state : state.rotate(mirrorIn.getRotation(state.getValue(FACING)));
    }
}