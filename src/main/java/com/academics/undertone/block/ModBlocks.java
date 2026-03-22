package com.academics.undertone.block;

import com.academics.undertone.Undertone;
import com.academics.undertone.item.ModItems;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Undertone.MODID); // create new deferred register for blocks with our modid.

    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) { // Helper method to register a block and its corresponding block item at the same time. This is not strictly necessary, but it is a common pattern that helps reduce code duplication and ensures that every block has a corresponding item.
        DeferredBlock<T> deferredBlock = BLOCKS.register(name, block); // register the block with the deferred register and get the DeferredBlock object back. This object will be used to create the BlockItem and to reference the block elsewhere in the code.
        registerBlock(name, deferredBlock); // call the other registerBlock method to create and register the BlockItem for this block. The name of the item will be the same as the block, which is a common convention but can be customized if needed.
        return deferredBlock;
    }

    public static final DeferredBlock<Block> ZINC_ORE = registerBlock("zinc_ore", // register new block with name "zinc_ore" and properties defined in the supplier function
            () -> new DropExperienceBlock(UniformInt.of(2,4), BlockBehaviour.Properties.of().requiresCorrectToolForDrops().strength(2.0F, 0.0F).sound(SoundType.STONE)));

    public static final DeferredBlock<Block> ZINC_BLOCK = registerBlock("zinc_block", // same as above
            () -> new Block(BlockBehaviour.Properties.of().requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.METAL)));

    private static <T extends Block> void registerBlock(String name, DeferredBlock<T> block) { // Helper method to register a BlockItem for a block. This is called from the other registerBlock method, which ensures that every block registered with that method will have a corresponding item.
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties())); // See line 21.
    }


    public static void register(IEventBus bus) { // this is to allow the game to register our blocks at the appropriate time during mod loading.
        BLOCKS.register(bus);
    }
}
