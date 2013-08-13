package teamcerberus.cerberuscore.multiblock;

import net.minecraft.block.Block;
import net.minecraft.block.BlockGrass;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import teamcerberus.cerberuscore.CerberusCoreSubcriber;
import teamcerberus.cerberuscore.render.CerbBlockRenderer;

public class MBRenderer extends CerbBlockRenderer {
	public static MBRenderer	instance	= new MBRenderer();

	@Override
	public void renderWorldBlock(RenderBlocks renderblocks,
			IBlockAccess paramIBlockAccess, int x, int y, int z, int meta,
			Block block) {
		TileMultiblock tile = (TileMultiblock) paramIBlockAccess
				.getBlockTileEntity(x, y, z);
		for (MBBox box : tile.getBoxes()) {
			renderblocks
					.setOverrideBlockTexture(CerberusCoreSubcriber.testIcon);
			renderblocks.flipTexture = true;
			block.setBlockBounds(box.getX(), box.getY(), box.getZ(), box.getX()
					+ box.getWidth(), box.getY() + box.getHeight(), box.getZ()
					+ box.getDepth());
			renderblocks.setRenderBoundsFromBlock(block);

			int l = block.colorMultiplier(paramIBlockAccess, x, y, z);
			float f = (float) (l >> 16 & 255) / 255.0F;
			float f1 = (float) (l >> 8 & 255) / 255.0F;
			float f2 = (float) (l & 255) / 255.0F;

			if (EntityRenderer.anaglyphEnable) {
				float f3 = (f * 30.0F + f1 * 59.0F + f2 * 11.0F) / 100.0F;
				float f4 = (f * 30.0F + f1 * 70.0F) / 100.0F;
				float f5 = (f * 30.0F + f2 * 70.0F) / 100.0F;
				f = f3;
				f1 = f4;
				f2 = f5;
			}

			specialStandardBlockRenderer(renderblocks, block, x, y, z, f, f1,
					f2);

			renderblocks.clearOverrideBlockTexture();
		}
		block.setBlockBounds(0f, 0f, 0f, 1f, 1f, 1f);
		renderblocks.clearOverrideBlockTexture();
	}

	@Override
	public void renderInvBlock(RenderBlocks paramRenderBlocks, int paramInt,
			Block block) {
		// TODO Auto-generated method stub

	}

	public boolean specialStandardBlockRenderer(RenderBlocks engine,
			Block par1Block, int par2, int par3, int par4, float par5,
			float par6, float par7) {
		engine.enableAO = true;
		boolean flag = false;
		float f3 = 0.0F;
		float f4 = 0.0F;
		float f5 = 0.0F;
		float f6 = 0.0F;
		boolean flag1 = true;
		int l = par1Block.getMixedBrightnessForBlock(engine.blockAccess, par2,
				par3, par4);
		Tessellator tessellator = Tessellator.instance;
		tessellator.setBrightness(983055);

		if (engine.getBlockIcon(par1Block).getIconName().equals("grass_top")) {
			flag1 = false;
		} else if (engine.hasOverrideBlockTexture()) {
			flag1 = false;
		}

		boolean flag2;
		boolean flag3;
		boolean flag4;
		boolean flag5;
		float f7;
		int i1;

		if (engine.renderAllFaces
				|| par1Block.shouldSideBeRendered(engine.blockAccess, par2,
						par3 - 1, par4, 0)) {
			if (engine.renderMinY <= 0.0D) {
				--par3;
			}

			engine.aoBrightnessXYNN = par1Block.getMixedBrightnessForBlock(
					engine.blockAccess, par2 - 1, par3, par4);
			engine.aoBrightnessYZNN = par1Block.getMixedBrightnessForBlock(
					engine.blockAccess, par2, par3, par4 - 1);
			engine.aoBrightnessYZNP = par1Block.getMixedBrightnessForBlock(
					engine.blockAccess, par2, par3, par4 + 1);
			engine.aoBrightnessXYPN = par1Block.getMixedBrightnessForBlock(
					engine.blockAccess, par2 + 1, par3, par4);
			engine.aoLightValueScratchXYNN = par1Block
					.getAmbientOcclusionLightValue(engine.blockAccess,
							par2 - 1, par3, par4);
			engine.aoLightValueScratchYZNN = par1Block
					.getAmbientOcclusionLightValue(engine.blockAccess, par2,
							par3, par4 - 1);
			engine.aoLightValueScratchYZNP = par1Block
					.getAmbientOcclusionLightValue(engine.blockAccess, par2,
							par3, par4 + 1);
			engine.aoLightValueScratchXYPN = par1Block
					.getAmbientOcclusionLightValue(engine.blockAccess,
							par2 + 1, par3, par4);
			flag3 = Block.canBlockGrass[engine.blockAccess.getBlockId(par2 + 1,
					par3 - 1, par4)];
			flag2 = Block.canBlockGrass[engine.blockAccess.getBlockId(par2 - 1,
					par3 - 1, par4)];
			flag5 = Block.canBlockGrass[engine.blockAccess.getBlockId(par2,
					par3 - 1, par4 + 1)];
			flag4 = Block.canBlockGrass[engine.blockAccess.getBlockId(par2,
					par3 - 1, par4 - 1)];

			if (!flag4 && !flag2) {
				engine.aoLightValueScratchXYZNNN = engine.aoLightValueScratchXYNN;
				engine.aoBrightnessXYZNNN = engine.aoBrightnessXYNN;
			} else {
				engine.aoLightValueScratchXYZNNN = par1Block
						.getAmbientOcclusionLightValue(engine.blockAccess,
								par2 - 1, par3, par4 - 1);
				engine.aoBrightnessXYZNNN = par1Block
						.getMixedBrightnessForBlock(engine.blockAccess,
								par2 - 1, par3, par4 - 1);
			}

			if (!flag5 && !flag2) {
				engine.aoLightValueScratchXYZNNP = engine.aoLightValueScratchXYNN;
				engine.aoBrightnessXYZNNP = engine.aoBrightnessXYNN;
			} else {
				engine.aoLightValueScratchXYZNNP = par1Block
						.getAmbientOcclusionLightValue(engine.blockAccess,
								par2 - 1, par3, par4 + 1);
				engine.aoBrightnessXYZNNP = par1Block
						.getMixedBrightnessForBlock(engine.blockAccess,
								par2 - 1, par3, par4 + 1);
			}

			if (!flag4 && !flag3) {
				engine.aoLightValueScratchXYZPNN = engine.aoLightValueScratchXYPN;
				engine.aoBrightnessXYZPNN = engine.aoBrightnessXYPN;
			} else {
				engine.aoLightValueScratchXYZPNN = par1Block
						.getAmbientOcclusionLightValue(engine.blockAccess,
								par2 + 1, par3, par4 - 1);
				engine.aoBrightnessXYZPNN = par1Block
						.getMixedBrightnessForBlock(engine.blockAccess,
								par2 + 1, par3, par4 - 1);
			}

			if (!flag5 && !flag3) {
				engine.aoLightValueScratchXYZPNP = engine.aoLightValueScratchXYPN;
				engine.aoBrightnessXYZPNP = engine.aoBrightnessXYPN;
			} else {
				engine.aoLightValueScratchXYZPNP = par1Block
						.getAmbientOcclusionLightValue(engine.blockAccess,
								par2 + 1, par3, par4 + 1);
				engine.aoBrightnessXYZPNP = par1Block
						.getMixedBrightnessForBlock(engine.blockAccess,
								par2 + 1, par3, par4 + 1);
			}

			if (engine.renderMinY <= 0.0D) {
				++par3;
			}

			i1 = l;

			if (engine.renderMinY <= 0.0D
					|| !engine.blockAccess.isBlockOpaqueCube(par2, par3 - 1,
							par4)) {
				i1 = par1Block.getMixedBrightnessForBlock(engine.blockAccess,
						par2, par3 - 1, par4);
			}

			f7 = par1Block.getAmbientOcclusionLightValue(engine.blockAccess,
					par2, par3 - 1, par4);
			f3 = (engine.aoLightValueScratchXYZNNP
					+ engine.aoLightValueScratchXYNN
					+ engine.aoLightValueScratchYZNP + f7) / 4.0F;
			f6 = (engine.aoLightValueScratchYZNP + f7
					+ engine.aoLightValueScratchXYZPNP + engine.aoLightValueScratchXYPN) / 4.0F;
			f5 = (f7 + engine.aoLightValueScratchYZNN
					+ engine.aoLightValueScratchXYPN + engine.aoLightValueScratchXYZPNN) / 4.0F;
			f4 = (engine.aoLightValueScratchXYNN
					+ engine.aoLightValueScratchXYZNNN + f7 + engine.aoLightValueScratchYZNN) / 4.0F;
			engine.brightnessTopLeft = engine.getAoBrightness(
					engine.aoBrightnessXYZNNP, engine.aoBrightnessXYNN,
					engine.aoBrightnessYZNP, i1);
			engine.brightnessTopRight = engine.getAoBrightness(
					engine.aoBrightnessYZNP, engine.aoBrightnessXYZPNP,
					engine.aoBrightnessXYPN, i1);
			engine.brightnessBottomRight = engine.getAoBrightness(
					engine.aoBrightnessYZNN, engine.aoBrightnessXYPN,
					engine.aoBrightnessXYZPNN, i1);
			engine.brightnessBottomLeft = engine.getAoBrightness(
					engine.aoBrightnessXYNN, engine.aoBrightnessXYZNNN,
					engine.aoBrightnessYZNN, i1);

			if (flag1) {
				engine.colorRedTopLeft = engine.colorRedBottomLeft = engine.colorRedBottomRight = engine.colorRedTopRight = par5 * 0.5F;
				engine.colorGreenTopLeft = engine.colorGreenBottomLeft = engine.colorGreenBottomRight = engine.colorGreenTopRight = par6 * 0.5F;
				engine.colorBlueTopLeft = engine.colorBlueBottomLeft = engine.colorBlueBottomRight = engine.colorBlueTopRight = par7 * 0.5F;
			} else {
				engine.colorRedTopLeft = engine.colorRedBottomLeft = engine.colorRedBottomRight = engine.colorRedTopRight = 0.5F;
				engine.colorGreenTopLeft = engine.colorGreenBottomLeft = engine.colorGreenBottomRight = engine.colorGreenTopRight = 0.5F;
				engine.colorBlueTopLeft = engine.colorBlueBottomLeft = engine.colorBlueBottomRight = engine.colorBlueTopRight = 0.5F;
			}

			engine.colorRedTopLeft *= f3;
			engine.colorGreenTopLeft *= f3;
			engine.colorBlueTopLeft *= f3;
			engine.colorRedBottomLeft *= f4;
			engine.colorGreenBottomLeft *= f4;
			engine.colorBlueBottomLeft *= f4;
			engine.colorRedBottomRight *= f5;
			engine.colorGreenBottomRight *= f5;
			engine.colorBlueBottomRight *= f5;
			engine.colorRedTopRight *= f6;
			engine.colorGreenTopRight *= f6;
			engine.colorBlueTopRight *= f6;
			engine.renderFaceYNeg(par1Block, (double) par2, (double) par3,
					(double) par4, engine.getBlockIcon(par1Block,
							engine.blockAccess, par2, par3, par4, 0));
			flag = true;
		}

		if (engine.renderAllFaces
				|| par1Block.shouldSideBeRendered(engine.blockAccess, par2,
						par3 + 1, par4, 1)) {
			if (engine.renderMaxY >= 1.0D) {
				++par3;
			}

			engine.aoBrightnessXYNP = par1Block.getMixedBrightnessForBlock(
					engine.blockAccess, par2 - 1, par3, par4);
			engine.aoBrightnessXYPP = par1Block.getMixedBrightnessForBlock(
					engine.blockAccess, par2 + 1, par3, par4);
			engine.aoBrightnessYZPN = par1Block.getMixedBrightnessForBlock(
					engine.blockAccess, par2, par3, par4 - 1);
			engine.aoBrightnessYZPP = par1Block.getMixedBrightnessForBlock(
					engine.blockAccess, par2, par3, par4 + 1);
			engine.aoLightValueScratchXYNP = par1Block
					.getAmbientOcclusionLightValue(engine.blockAccess,
							par2 - 1, par3, par4);
			engine.aoLightValueScratchXYPP = par1Block
					.getAmbientOcclusionLightValue(engine.blockAccess,
							par2 + 1, par3, par4);
			engine.aoLightValueScratchYZPN = par1Block
					.getAmbientOcclusionLightValue(engine.blockAccess, par2,
							par3, par4 - 1);
			engine.aoLightValueScratchYZPP = par1Block
					.getAmbientOcclusionLightValue(engine.blockAccess, par2,
							par3, par4 + 1);
			flag3 = Block.canBlockGrass[engine.blockAccess.getBlockId(par2 + 1,
					par3 + 1, par4)];
			flag2 = Block.canBlockGrass[engine.blockAccess.getBlockId(par2 - 1,
					par3 + 1, par4)];
			flag5 = Block.canBlockGrass[engine.blockAccess.getBlockId(par2,
					par3 + 1, par4 + 1)];
			flag4 = Block.canBlockGrass[engine.blockAccess.getBlockId(par2,
					par3 + 1, par4 - 1)];

			if (!flag4 && !flag2) {
				engine.aoLightValueScratchXYZNPN = engine.aoLightValueScratchXYNP;
				engine.aoBrightnessXYZNPN = engine.aoBrightnessXYNP;
			} else {
				engine.aoLightValueScratchXYZNPN = par1Block
						.getAmbientOcclusionLightValue(engine.blockAccess,
								par2 - 1, par3, par4 - 1);
				engine.aoBrightnessXYZNPN = par1Block
						.getMixedBrightnessForBlock(engine.blockAccess,
								par2 - 1, par3, par4 - 1);
			}

			if (!flag4 && !flag3) {
				engine.aoLightValueScratchXYZPPN = engine.aoLightValueScratchXYPP;
				engine.aoBrightnessXYZPPN = engine.aoBrightnessXYPP;
			} else {
				engine.aoLightValueScratchXYZPPN = par1Block
						.getAmbientOcclusionLightValue(engine.blockAccess,
								par2 + 1, par3, par4 - 1);
				engine.aoBrightnessXYZPPN = par1Block
						.getMixedBrightnessForBlock(engine.blockAccess,
								par2 + 1, par3, par4 - 1);
			}

			if (!flag5 && !flag2) {
				engine.aoLightValueScratchXYZNPP = engine.aoLightValueScratchXYNP;
				engine.aoBrightnessXYZNPP = engine.aoBrightnessXYNP;
			} else {
				engine.aoLightValueScratchXYZNPP = par1Block
						.getAmbientOcclusionLightValue(engine.blockAccess,
								par2 - 1, par3, par4 + 1);
				engine.aoBrightnessXYZNPP = par1Block
						.getMixedBrightnessForBlock(engine.blockAccess,
								par2 - 1, par3, par4 + 1);
			}

			if (!flag5 && !flag3) {
				engine.aoLightValueScratchXYZPPP = engine.aoLightValueScratchXYPP;
				engine.aoBrightnessXYZPPP = engine.aoBrightnessXYPP;
			} else {
				engine.aoLightValueScratchXYZPPP = par1Block
						.getAmbientOcclusionLightValue(engine.blockAccess,
								par2 + 1, par3, par4 + 1);
				engine.aoBrightnessXYZPPP = par1Block
						.getMixedBrightnessForBlock(engine.blockAccess,
								par2 + 1, par3, par4 + 1);
			}

			if (engine.renderMaxY >= 1.0D) {
				--par3;
			}

			i1 = l;

			if (engine.renderMaxY >= 1.0D
					|| !engine.blockAccess.isBlockOpaqueCube(par2, par3 + 1,
							par4)) {
				i1 = par1Block.getMixedBrightnessForBlock(engine.blockAccess,
						par2, par3 + 1, par4);
			}

			f7 = par1Block.getAmbientOcclusionLightValue(engine.blockAccess,
					par2, par3 + 1, par4);
			f6 = (engine.aoLightValueScratchXYZNPP
					+ engine.aoLightValueScratchXYNP
					+ engine.aoLightValueScratchYZPP + f7) / 4.0F;
			f3 = (engine.aoLightValueScratchYZPP + f7
					+ engine.aoLightValueScratchXYZPPP + engine.aoLightValueScratchXYPP) / 4.0F;
			f4 = (f7 + engine.aoLightValueScratchYZPN
					+ engine.aoLightValueScratchXYPP + engine.aoLightValueScratchXYZPPN) / 4.0F;
			f5 = (engine.aoLightValueScratchXYNP
					+ engine.aoLightValueScratchXYZNPN + f7 + engine.aoLightValueScratchYZPN) / 4.0F;
			engine.brightnessTopRight = engine.getAoBrightness(
					engine.aoBrightnessXYZNPP, engine.aoBrightnessXYNP,
					engine.aoBrightnessYZPP, i1);
			engine.brightnessTopLeft = engine.getAoBrightness(
					engine.aoBrightnessYZPP, engine.aoBrightnessXYZPPP,
					engine.aoBrightnessXYPP, i1);
			engine.brightnessBottomLeft = engine.getAoBrightness(
					engine.aoBrightnessYZPN, engine.aoBrightnessXYPP,
					engine.aoBrightnessXYZPPN, i1);
			engine.brightnessBottomRight = engine.getAoBrightness(
					engine.aoBrightnessXYNP, engine.aoBrightnessXYZNPN,
					engine.aoBrightnessYZPN, i1);
			engine.colorRedTopLeft = engine.colorRedBottomLeft = engine.colorRedBottomRight = engine.colorRedTopRight = par5;
			engine.colorGreenTopLeft = engine.colorGreenBottomLeft = engine.colorGreenBottomRight = engine.colorGreenTopRight = par6;
			engine.colorBlueTopLeft = engine.colorBlueBottomLeft = engine.colorBlueBottomRight = engine.colorBlueTopRight = par7;
			engine.colorRedTopLeft *= f3;
			engine.colorGreenTopLeft *= f3;
			engine.colorBlueTopLeft *= f3;
			engine.colorRedBottomLeft *= f4;
			engine.colorGreenBottomLeft *= f4;
			engine.colorBlueBottomLeft *= f4;
			engine.colorRedBottomRight *= f5;
			engine.colorGreenBottomRight *= f5;
			engine.colorBlueBottomRight *= f5;
			engine.colorRedTopRight *= f6;
			engine.colorGreenTopRight *= f6;
			engine.colorBlueTopRight *= f6;
			engine.renderFaceYPos(par1Block, (double) par2, (double) par3,
					(double) par4, engine.getBlockIcon(par1Block,
							engine.blockAccess, par2, par3, par4, 1));
			flag = true;
		}

		float f8;
		float f9;
		float f10;
		float f11;
		int j1;
		int k1;
		int l1;
		int i2;
		Icon icon;

		if (engine.renderAllFaces
				|| par1Block.shouldSideBeRendered(engine.blockAccess, par2,
						par3, par4 - 1, 2)) {
			if (engine.renderMinZ <= 0.0D) {
				--par4;
			}

			engine.aoLightValueScratchXZNN = par1Block
					.getAmbientOcclusionLightValue(engine.blockAccess,
							par2 - 1, par3, par4);
			engine.aoLightValueScratchYZNN = par1Block
					.getAmbientOcclusionLightValue(engine.blockAccess, par2,
							par3 - 1, par4);
			engine.aoLightValueScratchYZPN = par1Block
					.getAmbientOcclusionLightValue(engine.blockAccess, par2,
							par3 + 1, par4);
			engine.aoLightValueScratchXZPN = par1Block
					.getAmbientOcclusionLightValue(engine.blockAccess,
							par2 + 1, par3, par4);
			engine.aoBrightnessXZNN = par1Block.getMixedBrightnessForBlock(
					engine.blockAccess, par2 - 1, par3, par4);
			engine.aoBrightnessYZNN = par1Block.getMixedBrightnessForBlock(
					engine.blockAccess, par2, par3 - 1, par4);
			engine.aoBrightnessYZPN = par1Block.getMixedBrightnessForBlock(
					engine.blockAccess, par2, par3 + 1, par4);
			engine.aoBrightnessXZPN = par1Block.getMixedBrightnessForBlock(
					engine.blockAccess, par2 + 1, par3, par4);
			flag3 = Block.canBlockGrass[engine.blockAccess.getBlockId(par2 + 1,
					par3, par4 - 1)];
			flag2 = Block.canBlockGrass[engine.blockAccess.getBlockId(par2 - 1,
					par3, par4 - 1)];
			flag5 = Block.canBlockGrass[engine.blockAccess.getBlockId(par2,
					par3 + 1, par4 - 1)];
			flag4 = Block.canBlockGrass[engine.blockAccess.getBlockId(par2,
					par3 - 1, par4 - 1)];

			if (!flag2 && !flag4) {
				engine.aoLightValueScratchXYZNNN = engine.aoLightValueScratchXZNN;
				engine.aoBrightnessXYZNNN = engine.aoBrightnessXZNN;
			} else {
				engine.aoLightValueScratchXYZNNN = par1Block
						.getAmbientOcclusionLightValue(engine.blockAccess,
								par2 - 1, par3 - 1, par4);
				engine.aoBrightnessXYZNNN = par1Block
						.getMixedBrightnessForBlock(engine.blockAccess,
								par2 - 1, par3 - 1, par4);
			}

			if (!flag2 && !flag5) {
				engine.aoLightValueScratchXYZNPN = engine.aoLightValueScratchXZNN;
				engine.aoBrightnessXYZNPN = engine.aoBrightnessXZNN;
			} else {
				engine.aoLightValueScratchXYZNPN = par1Block
						.getAmbientOcclusionLightValue(engine.blockAccess,
								par2 - 1, par3 + 1, par4);
				engine.aoBrightnessXYZNPN = par1Block
						.getMixedBrightnessForBlock(engine.blockAccess,
								par2 - 1, par3 + 1, par4);
			}

			if (!flag3 && !flag4) {
				engine.aoLightValueScratchXYZPNN = engine.aoLightValueScratchXZPN;
				engine.aoBrightnessXYZPNN = engine.aoBrightnessXZPN;
			} else {
				engine.aoLightValueScratchXYZPNN = par1Block
						.getAmbientOcclusionLightValue(engine.blockAccess,
								par2 + 1, par3 - 1, par4);
				engine.aoBrightnessXYZPNN = par1Block
						.getMixedBrightnessForBlock(engine.blockAccess,
								par2 + 1, par3 - 1, par4);
			}

			if (!flag3 && !flag5) {
				engine.aoLightValueScratchXYZPPN = engine.aoLightValueScratchXZPN;
				engine.aoBrightnessXYZPPN = engine.aoBrightnessXZPN;
			} else {
				engine.aoLightValueScratchXYZPPN = par1Block
						.getAmbientOcclusionLightValue(engine.blockAccess,
								par2 + 1, par3 + 1, par4);
				engine.aoBrightnessXYZPPN = par1Block
						.getMixedBrightnessForBlock(engine.blockAccess,
								par2 + 1, par3 + 1, par4);
			}

			if (engine.renderMinZ <= 0.0D) {
				++par4;
			}

			i1 = l;

			if (engine.renderMinZ <= 0.0D
					|| !engine.blockAccess.isBlockOpaqueCube(par2, par3,
							par4 - 1)) {
				i1 = par1Block.getMixedBrightnessForBlock(engine.blockAccess,
						par2, par3, par4 - 1);
			}

			f7 = par1Block.getAmbientOcclusionLightValue(engine.blockAccess,
					par2, par3, par4 - 1);
			f9 = (engine.aoLightValueScratchXZNN
					+ engine.aoLightValueScratchXYZNPN + f7 + engine.aoLightValueScratchYZPN) / 4.0F;
			f8 = (f7 + engine.aoLightValueScratchYZPN
					+ engine.aoLightValueScratchXZPN + engine.aoLightValueScratchXYZPPN) / 4.0F;
			f11 = (engine.aoLightValueScratchYZNN + f7
					+ engine.aoLightValueScratchXYZPNN + engine.aoLightValueScratchXZPN) / 4.0F;
			f10 = (engine.aoLightValueScratchXYZNNN
					+ engine.aoLightValueScratchXZNN
					+ engine.aoLightValueScratchYZNN + f7) / 4.0F;
			f3 = (float) ((double) f9 * engine.renderMaxY
					* (1.0D - engine.renderMinX) + (double) f8
					* engine.renderMinY * engine.renderMinX + (double) f11
					* (1.0D - engine.renderMaxY) * engine.renderMinX + (double) f10
					* (1.0D - engine.renderMaxY) * (1.0D - engine.renderMinX));
			f4 = (float) ((double) f9 * engine.renderMaxY
					* (1.0D - engine.renderMaxX) + (double) f8
					* engine.renderMaxY * engine.renderMaxX + (double) f11
					* (1.0D - engine.renderMaxY) * engine.renderMaxX + (double) f10
					* (1.0D - engine.renderMaxY) * (1.0D - engine.renderMaxX));
			f5 = (float) ((double) f9 * engine.renderMinY
					* (1.0D - engine.renderMaxX) + (double) f8
					* engine.renderMinY * engine.renderMaxX + (double) f11
					* (1.0D - engine.renderMinY) * engine.renderMaxX + (double) f10
					* (1.0D - engine.renderMinY) * (1.0D - engine.renderMaxX));
			f6 = (float) ((double) f9 * engine.renderMinY
					* (1.0D - engine.renderMinX) + (double) f8
					* engine.renderMinY * engine.renderMinX + (double) f11
					* (1.0D - engine.renderMinY) * engine.renderMinX + (double) f10
					* (1.0D - engine.renderMinY) * (1.0D - engine.renderMinX));
			k1 = engine.getAoBrightness(engine.aoBrightnessXZNN,
					engine.aoBrightnessXYZNPN, engine.aoBrightnessYZPN, i1);
			j1 = engine.getAoBrightness(engine.aoBrightnessYZPN,
					engine.aoBrightnessXZPN, engine.aoBrightnessXYZPPN, i1);
			i2 = engine.getAoBrightness(engine.aoBrightnessYZNN,
					engine.aoBrightnessXYZPNN, engine.aoBrightnessXZPN, i1);
			l1 = engine.getAoBrightness(engine.aoBrightnessXYZNNN,
					engine.aoBrightnessXZNN, engine.aoBrightnessYZNN, i1);
			engine.brightnessTopLeft = engine.mixAoBrightness(k1, j1, i2, l1,
					engine.renderMaxY * (1.0D - engine.renderMinX),
					engine.renderMaxY * engine.renderMinX,
					(1.0D - engine.renderMaxY) * engine.renderMinX,
					(1.0D - engine.renderMaxY) * (1.0D - engine.renderMinX));
			engine.brightnessBottomLeft = engine.mixAoBrightness(k1, j1, i2,
					l1, engine.renderMaxY * (1.0D - engine.renderMaxX),
					engine.renderMaxY * engine.renderMaxX,
					(1.0D - engine.renderMaxY) * engine.renderMaxX,
					(1.0D - engine.renderMaxY) * (1.0D - engine.renderMaxX));
			engine.brightnessBottomRight = engine.mixAoBrightness(k1, j1, i2,
					l1, engine.renderMinY * (1.0D - engine.renderMaxX),
					engine.renderMinY * engine.renderMaxX,
					(1.0D - engine.renderMinY) * engine.renderMaxX,
					(1.0D - engine.renderMinY) * (1.0D - engine.renderMaxX));
			engine.brightnessTopRight = engine.mixAoBrightness(k1, j1, i2, l1,
					engine.renderMinY * (1.0D - engine.renderMinX),
					engine.renderMinY * engine.renderMinX,
					(1.0D - engine.renderMinY) * engine.renderMinX,
					(1.0D - engine.renderMinY) * (1.0D - engine.renderMinX));

			if (flag1) {
				engine.colorRedTopLeft = engine.colorRedBottomLeft = engine.colorRedBottomRight = engine.colorRedTopRight = par5 * 0.8F;
				engine.colorGreenTopLeft = engine.colorGreenBottomLeft = engine.colorGreenBottomRight = engine.colorGreenTopRight = par6 * 0.8F;
				engine.colorBlueTopLeft = engine.colorBlueBottomLeft = engine.colorBlueBottomRight = engine.colorBlueTopRight = par7 * 0.8F;
			} else {
				engine.colorRedTopLeft = engine.colorRedBottomLeft = engine.colorRedBottomRight = engine.colorRedTopRight = 0.8F;
				engine.colorGreenTopLeft = engine.colorGreenBottomLeft = engine.colorGreenBottomRight = engine.colorGreenTopRight = 0.8F;
				engine.colorBlueTopLeft = engine.colorBlueBottomLeft = engine.colorBlueBottomRight = engine.colorBlueTopRight = 0.8F;
			}

			engine.colorRedTopLeft *= f3;
			engine.colorGreenTopLeft *= f3;
			engine.colorBlueTopLeft *= f3;
			engine.colorRedBottomLeft *= f4;
			engine.colorGreenBottomLeft *= f4;
			engine.colorBlueBottomLeft *= f4;
			engine.colorRedBottomRight *= f5;
			engine.colorGreenBottomRight *= f5;
			engine.colorBlueBottomRight *= f5;
			engine.colorRedTopRight *= f6;
			engine.colorGreenTopRight *= f6;
			engine.colorBlueTopRight *= f6;
			icon = engine.getBlockIcon(par1Block, engine.blockAccess, par2,
					par3, par4, 2);
			engine.renderFaceZNeg(par1Block, (double) par2, (double) par3,
					(double) par4, icon);

			if (RenderBlocks.fancyGrass
					&& icon.getIconName().equals("grass_side")
					&& !engine.hasOverrideBlockTexture()) {
				engine.colorRedTopLeft *= par5;
				engine.colorRedBottomLeft *= par5;
				engine.colorRedBottomRight *= par5;
				engine.colorRedTopRight *= par5;
				engine.colorGreenTopLeft *= par6;
				engine.colorGreenBottomLeft *= par6;
				engine.colorGreenBottomRight *= par6;
				engine.colorGreenTopRight *= par6;
				engine.colorBlueTopLeft *= par7;
				engine.colorBlueBottomLeft *= par7;
				engine.colorBlueBottomRight *= par7;
				engine.colorBlueTopRight *= par7;
				engine.renderFaceZNeg(par1Block, (double) par2, (double) par3,
						(double) par4, BlockGrass.getIconSideOverlay());
			}

			flag = true;
		}

		if (engine.renderAllFaces
				|| par1Block.shouldSideBeRendered(engine.blockAccess, par2,
						par3, par4 + 1, 3)) {
			if (engine.renderMaxZ >= 1.0D) {
				++par4;
			}

			engine.aoLightValueScratchXZNP = par1Block
					.getAmbientOcclusionLightValue(engine.blockAccess,
							par2 - 1, par3, par4);
			engine.aoLightValueScratchXZPP = par1Block
					.getAmbientOcclusionLightValue(engine.blockAccess,
							par2 + 1, par3, par4);
			engine.aoLightValueScratchYZNP = par1Block
					.getAmbientOcclusionLightValue(engine.blockAccess, par2,
							par3 - 1, par4);
			engine.aoLightValueScratchYZPP = par1Block
					.getAmbientOcclusionLightValue(engine.blockAccess, par2,
							par3 + 1, par4);
			engine.aoBrightnessXZNP = par1Block.getMixedBrightnessForBlock(
					engine.blockAccess, par2 - 1, par3, par4);
			engine.aoBrightnessXZPP = par1Block.getMixedBrightnessForBlock(
					engine.blockAccess, par2 + 1, par3, par4);
			engine.aoBrightnessYZNP = par1Block.getMixedBrightnessForBlock(
					engine.blockAccess, par2, par3 - 1, par4);
			engine.aoBrightnessYZPP = par1Block.getMixedBrightnessForBlock(
					engine.blockAccess, par2, par3 + 1, par4);
			flag3 = Block.canBlockGrass[engine.blockAccess.getBlockId(par2 + 1,
					par3, par4 + 1)];
			flag2 = Block.canBlockGrass[engine.blockAccess.getBlockId(par2 - 1,
					par3, par4 + 1)];
			flag5 = Block.canBlockGrass[engine.blockAccess.getBlockId(par2,
					par3 + 1, par4 + 1)];
			flag4 = Block.canBlockGrass[engine.blockAccess.getBlockId(par2,
					par3 - 1, par4 + 1)];

			if (!flag2 && !flag4) {
				engine.aoLightValueScratchXYZNNP = engine.aoLightValueScratchXZNP;
				engine.aoBrightnessXYZNNP = engine.aoBrightnessXZNP;
			} else {
				engine.aoLightValueScratchXYZNNP = par1Block
						.getAmbientOcclusionLightValue(engine.blockAccess,
								par2 - 1, par3 - 1, par4);
				engine.aoBrightnessXYZNNP = par1Block
						.getMixedBrightnessForBlock(engine.blockAccess,
								par2 - 1, par3 - 1, par4);
			}

			if (!flag2 && !flag5) {
				engine.aoLightValueScratchXYZNPP = engine.aoLightValueScratchXZNP;
				engine.aoBrightnessXYZNPP = engine.aoBrightnessXZNP;
			} else {
				engine.aoLightValueScratchXYZNPP = par1Block
						.getAmbientOcclusionLightValue(engine.blockAccess,
								par2 - 1, par3 + 1, par4);
				engine.aoBrightnessXYZNPP = par1Block
						.getMixedBrightnessForBlock(engine.blockAccess,
								par2 - 1, par3 + 1, par4);
			}

			if (!flag3 && !flag4) {
				engine.aoLightValueScratchXYZPNP = engine.aoLightValueScratchXZPP;
				engine.aoBrightnessXYZPNP = engine.aoBrightnessXZPP;
			} else {
				engine.aoLightValueScratchXYZPNP = par1Block
						.getAmbientOcclusionLightValue(engine.blockAccess,
								par2 + 1, par3 - 1, par4);
				engine.aoBrightnessXYZPNP = par1Block
						.getMixedBrightnessForBlock(engine.blockAccess,
								par2 + 1, par3 - 1, par4);
			}

			if (!flag3 && !flag5) {
				engine.aoLightValueScratchXYZPPP = engine.aoLightValueScratchXZPP;
				engine.aoBrightnessXYZPPP = engine.aoBrightnessXZPP;
			} else {
				engine.aoLightValueScratchXYZPPP = par1Block
						.getAmbientOcclusionLightValue(engine.blockAccess,
								par2 + 1, par3 + 1, par4);
				engine.aoBrightnessXYZPPP = par1Block
						.getMixedBrightnessForBlock(engine.blockAccess,
								par2 + 1, par3 + 1, par4);
			}

			if (engine.renderMaxZ >= 1.0D) {
				--par4;
			}

			i1 = l;

			if (engine.renderMaxZ >= 1.0D
					|| !engine.blockAccess.isBlockOpaqueCube(par2, par3,
							par4 + 1)) {
				i1 = par1Block.getMixedBrightnessForBlock(engine.blockAccess,
						par2, par3, par4 + 1);
			}

			f7 = par1Block.getAmbientOcclusionLightValue(engine.blockAccess,
					par2, par3, par4 + 1);
			f9 = (engine.aoLightValueScratchXZNP
					+ engine.aoLightValueScratchXYZNPP + f7 + engine.aoLightValueScratchYZPP) / 4.0F;
			f8 = (f7 + engine.aoLightValueScratchYZPP
					+ engine.aoLightValueScratchXZPP + engine.aoLightValueScratchXYZPPP) / 4.0F;
			f11 = (engine.aoLightValueScratchYZNP + f7
					+ engine.aoLightValueScratchXYZPNP + engine.aoLightValueScratchXZPP) / 4.0F;
			f10 = (engine.aoLightValueScratchXYZNNP
					+ engine.aoLightValueScratchXZNP
					+ engine.aoLightValueScratchYZNP + f7) / 4.0F;
			f3 = (float) ((double) f9 * engine.renderMaxY
					* (1.0D - engine.renderMinX) + (double) f8
					* engine.renderMaxY * engine.renderMinX + (double) f11
					* (1.0D - engine.renderMaxY) * engine.renderMinX + (double) f10
					* (1.0D - engine.renderMaxY) * (1.0D - engine.renderMinX));
			f4 = (float) ((double) f9 * engine.renderMinY
					* (1.0D - engine.renderMinX) + (double) f8
					* engine.renderMinY * engine.renderMinX + (double) f11
					* (1.0D - engine.renderMinY) * engine.renderMinX + (double) f10
					* (1.0D - engine.renderMinY) * (1.0D - engine.renderMinX));
			f5 = (float) ((double) f9 * engine.renderMinY
					* (1.0D - engine.renderMaxX) + (double) f8
					* engine.renderMinY * engine.renderMaxX + (double) f11
					* (1.0D - engine.renderMinY) * engine.renderMaxX + (double) f10
					* (1.0D - engine.renderMinY) * (1.0D - engine.renderMaxX));
			f6 = (float) ((double) f9 * engine.renderMaxY
					* (1.0D - engine.renderMaxX) + (double) f8
					* engine.renderMaxY * engine.renderMaxX + (double) f11
					* (1.0D - engine.renderMaxY) * engine.renderMaxX + (double) f10
					* (1.0D - engine.renderMaxY) * (1.0D - engine.renderMaxX));
			k1 = engine.getAoBrightness(engine.aoBrightnessXZNP,
					engine.aoBrightnessXYZNPP, engine.aoBrightnessYZPP, i1);
			j1 = engine.getAoBrightness(engine.aoBrightnessYZPP,
					engine.aoBrightnessXZPP, engine.aoBrightnessXYZPPP, i1);
			i2 = engine.getAoBrightness(engine.aoBrightnessYZNP,
					engine.aoBrightnessXYZPNP, engine.aoBrightnessXZPP, i1);
			l1 = engine.getAoBrightness(engine.aoBrightnessXYZNNP,
					engine.aoBrightnessXZNP, engine.aoBrightnessYZNP, i1);
			engine.brightnessTopLeft = engine.mixAoBrightness(k1, l1, i2, j1,
					engine.renderMaxY * (1.0D - engine.renderMinX),
					(1.0D - engine.renderMaxY) * (1.0D - engine.renderMinX),
					(1.0D - engine.renderMaxY) * engine.renderMinX,
					engine.renderMaxY * engine.renderMinX);
			engine.brightnessBottomLeft = engine.mixAoBrightness(k1, l1, i2,
					j1, engine.renderMinY * (1.0D - engine.renderMinX),
					(1.0D - engine.renderMinY) * (1.0D - engine.renderMinX),
					(1.0D - engine.renderMinY) * engine.renderMinX,
					engine.renderMinY * engine.renderMinX);
			engine.brightnessBottomRight = engine.mixAoBrightness(k1, l1, i2,
					j1, engine.renderMinY * (1.0D - engine.renderMaxX),
					(1.0D - engine.renderMinY) * (1.0D - engine.renderMaxX),
					(1.0D - engine.renderMinY) * engine.renderMaxX,
					engine.renderMinY * engine.renderMaxX);
			engine.brightnessTopRight = engine.mixAoBrightness(k1, l1, i2, j1,
					engine.renderMaxY * (1.0D - engine.renderMaxX),
					(1.0D - engine.renderMaxY) * (1.0D - engine.renderMaxX),
					(1.0D - engine.renderMaxY) * engine.renderMaxX,
					engine.renderMaxY * engine.renderMaxX);

			if (flag1) {
				engine.colorRedTopLeft = engine.colorRedBottomLeft = engine.colorRedBottomRight = engine.colorRedTopRight = par5 * 0.8F;
				engine.colorGreenTopLeft = engine.colorGreenBottomLeft = engine.colorGreenBottomRight = engine.colorGreenTopRight = par6 * 0.8F;
				engine.colorBlueTopLeft = engine.colorBlueBottomLeft = engine.colorBlueBottomRight = engine.colorBlueTopRight = par7 * 0.8F;
			} else {
				engine.colorRedTopLeft = engine.colorRedBottomLeft = engine.colorRedBottomRight = engine.colorRedTopRight = 0.8F;
				engine.colorGreenTopLeft = engine.colorGreenBottomLeft = engine.colorGreenBottomRight = engine.colorGreenTopRight = 0.8F;
				engine.colorBlueTopLeft = engine.colorBlueBottomLeft = engine.colorBlueBottomRight = engine.colorBlueTopRight = 0.8F;
			}

			engine.colorRedTopLeft *= f3;
			engine.colorGreenTopLeft *= f3;
			engine.colorBlueTopLeft *= f3;
			engine.colorRedBottomLeft *= f4;
			engine.colorGreenBottomLeft *= f4;
			engine.colorBlueBottomLeft *= f4;
			engine.colorRedBottomRight *= f5;
			engine.colorGreenBottomRight *= f5;
			engine.colorBlueBottomRight *= f5;
			engine.colorRedTopRight *= f6;
			engine.colorGreenTopRight *= f6;
			engine.colorBlueTopRight *= f6;
			icon = engine.getBlockIcon(par1Block, engine.blockAccess, par2,
					par3, par4, 3);

			renderFaceZPos(engine, par1Block, (double) par2, (double) par3,
					(double) par4, engine.getBlockIcon(par1Block,
							engine.blockAccess, par2, par3, par4, 3));

			if (RenderBlocks.fancyGrass
					&& icon.getIconName().equals("grass_side")
					&& !engine.hasOverrideBlockTexture()) {
				engine.colorRedTopLeft *= par5;
				engine.colorRedBottomLeft *= par5;
				engine.colorRedBottomRight *= par5;
				engine.colorRedTopRight *= par5;
				engine.colorGreenTopLeft *= par6;
				engine.colorGreenBottomLeft *= par6;
				engine.colorGreenBottomRight *= par6;
				engine.colorGreenTopRight *= par6;
				engine.colorBlueTopLeft *= par7;
				engine.colorBlueBottomLeft *= par7;
				engine.colorBlueBottomRight *= par7;
				engine.colorBlueTopRight *= par7;
				renderFaceZPos(engine, par1Block, (double) par2, (double) par3,
						(double) par4, BlockGrass.getIconSideOverlay());
			}

			flag = true;
		}

		if (engine.renderAllFaces
				|| par1Block.shouldSideBeRendered(engine.blockAccess, par2 - 1,
						par3, par4, 4)) {
			if (engine.renderMinX <= 0.0D) {
				--par2;
			}

			engine.aoLightValueScratchXYNN = par1Block
					.getAmbientOcclusionLightValue(engine.blockAccess, par2,
							par3 - 1, par4);
			engine.aoLightValueScratchXZNN = par1Block
					.getAmbientOcclusionLightValue(engine.blockAccess, par2,
							par3, par4 - 1);
			engine.aoLightValueScratchXZNP = par1Block
					.getAmbientOcclusionLightValue(engine.blockAccess, par2,
							par3, par4 + 1);
			engine.aoLightValueScratchXYNP = par1Block
					.getAmbientOcclusionLightValue(engine.blockAccess, par2,
							par3 + 1, par4);
			engine.aoBrightnessXYNN = par1Block.getMixedBrightnessForBlock(
					engine.blockAccess, par2, par3 - 1, par4);
			engine.aoBrightnessXZNN = par1Block.getMixedBrightnessForBlock(
					engine.blockAccess, par2, par3, par4 - 1);
			engine.aoBrightnessXZNP = par1Block.getMixedBrightnessForBlock(
					engine.blockAccess, par2, par3, par4 + 1);
			engine.aoBrightnessXYNP = par1Block.getMixedBrightnessForBlock(
					engine.blockAccess, par2, par3 + 1, par4);
			flag3 = Block.canBlockGrass[engine.blockAccess.getBlockId(par2 - 1,
					par3 + 1, par4)];
			flag2 = Block.canBlockGrass[engine.blockAccess.getBlockId(par2 - 1,
					par3 - 1, par4)];
			flag5 = Block.canBlockGrass[engine.blockAccess.getBlockId(par2 - 1,
					par3, par4 - 1)];
			flag4 = Block.canBlockGrass[engine.blockAccess.getBlockId(par2 - 1,
					par3, par4 + 1)];

			if (!flag5 && !flag2) {
				engine.aoLightValueScratchXYZNNN = engine.aoLightValueScratchXZNN;
				engine.aoBrightnessXYZNNN = engine.aoBrightnessXZNN;
			} else {
				engine.aoLightValueScratchXYZNNN = par1Block
						.getAmbientOcclusionLightValue(engine.blockAccess,
								par2, par3 - 1, par4 - 1);
				engine.aoBrightnessXYZNNN = par1Block
						.getMixedBrightnessForBlock(engine.blockAccess, par2,
								par3 - 1, par4 - 1);
			}

			if (!flag4 && !flag2) {
				engine.aoLightValueScratchXYZNNP = engine.aoLightValueScratchXZNP;
				engine.aoBrightnessXYZNNP = engine.aoBrightnessXZNP;
			} else {
				engine.aoLightValueScratchXYZNNP = par1Block
						.getAmbientOcclusionLightValue(engine.blockAccess,
								par2, par3 - 1, par4 + 1);
				engine.aoBrightnessXYZNNP = par1Block
						.getMixedBrightnessForBlock(engine.blockAccess, par2,
								par3 - 1, par4 + 1);
			}

			if (!flag5 && !flag3) {
				engine.aoLightValueScratchXYZNPN = engine.aoLightValueScratchXZNN;
				engine.aoBrightnessXYZNPN = engine.aoBrightnessXZNN;
			} else {
				engine.aoLightValueScratchXYZNPN = par1Block
						.getAmbientOcclusionLightValue(engine.blockAccess,
								par2, par3 + 1, par4 - 1);
				engine.aoBrightnessXYZNPN = par1Block
						.getMixedBrightnessForBlock(engine.blockAccess, par2,
								par3 + 1, par4 - 1);
			}

			if (!flag4 && !flag3) {
				engine.aoLightValueScratchXYZNPP = engine.aoLightValueScratchXZNP;
				engine.aoBrightnessXYZNPP = engine.aoBrightnessXZNP;
			} else {
				engine.aoLightValueScratchXYZNPP = par1Block
						.getAmbientOcclusionLightValue(engine.blockAccess,
								par2, par3 + 1, par4 + 1);
				engine.aoBrightnessXYZNPP = par1Block
						.getMixedBrightnessForBlock(engine.blockAccess, par2,
								par3 + 1, par4 + 1);
			}

			if (engine.renderMinX <= 0.0D) {
				++par2;
			}

			i1 = l;

			if (engine.renderMinX <= 0.0D
					|| !engine.blockAccess.isBlockOpaqueCube(par2 - 1, par3,
							par4)) {
				i1 = par1Block.getMixedBrightnessForBlock(engine.blockAccess,
						par2 - 1, par3, par4);
			}

			f7 = par1Block.getAmbientOcclusionLightValue(engine.blockAccess,
					par2 - 1, par3, par4);
			f9 = (engine.aoLightValueScratchXYNN
					+ engine.aoLightValueScratchXYZNNP + f7 + engine.aoLightValueScratchXZNP) / 4.0F;
			f8 = (f7 + engine.aoLightValueScratchXZNP
					+ engine.aoLightValueScratchXYNP + engine.aoLightValueScratchXYZNPP) / 4.0F;
			f11 = (engine.aoLightValueScratchXZNN + f7
					+ engine.aoLightValueScratchXYZNPN + engine.aoLightValueScratchXYNP) / 4.0F;
			f10 = (engine.aoLightValueScratchXYZNNN
					+ engine.aoLightValueScratchXYNN
					+ engine.aoLightValueScratchXZNN + f7) / 4.0F;
			f3 = (float) ((double) f8 * engine.renderMaxY * engine.renderMaxZ
					+ (double) f11 * engine.renderMaxY
					* (1.0D - engine.renderMaxZ) + (double) f10
					* (1.0D - engine.renderMaxY) * (1.0D - engine.renderMaxZ) + (double) f9
					* (1.0D - engine.renderMaxY) * engine.renderMaxZ);
			f4 = (float) ((double) f8 * engine.renderMaxY * engine.renderMinZ
					+ (double) f11 * engine.renderMaxY
					* (1.0D - engine.renderMinZ) + (double) f10
					* (1.0D - engine.renderMaxY) * (1.0D - engine.renderMinZ) + (double) f9
					* (1.0D - engine.renderMaxY) * engine.renderMinZ);
			f5 = (float) ((double) f8 * engine.renderMinY * engine.renderMinZ
					+ (double) f11 * engine.renderMinY
					* (1.0D - engine.renderMinZ) + (double) f10
					* (1.0D - engine.renderMinY) * (1.0D - engine.renderMinZ) + (double) f9
					* (1.0D - engine.renderMinY) * engine.renderMinZ);
			f6 = (float) ((double) f8 * engine.renderMinY * engine.renderMaxZ
					+ (double) f11 * engine.renderMinY
					* (1.0D - engine.renderMaxZ) + (double) f10
					* (1.0D - engine.renderMinY) * (1.0D - engine.renderMaxZ) + (double) f9
					* (1.0D - engine.renderMinY) * engine.renderMaxZ);
			k1 = engine.getAoBrightness(engine.aoBrightnessXYNN,
					engine.aoBrightnessXYZNNP, engine.aoBrightnessXZNP, i1);
			j1 = engine.getAoBrightness(engine.aoBrightnessXZNP,
					engine.aoBrightnessXYNP, engine.aoBrightnessXYZNPP, i1);
			i2 = engine.getAoBrightness(engine.aoBrightnessXZNN,
					engine.aoBrightnessXYZNPN, engine.aoBrightnessXYNP, i1);
			l1 = engine.getAoBrightness(engine.aoBrightnessXYZNNN,
					engine.aoBrightnessXYNN, engine.aoBrightnessXZNN, i1);
			engine.brightnessTopLeft = engine.mixAoBrightness(j1, i2, l1, k1,
					engine.renderMaxY * engine.renderMaxZ, engine.renderMaxY
							* (1.0D - engine.renderMaxZ),
					(1.0D - engine.renderMaxY) * (1.0D - engine.renderMaxZ),
					(1.0D - engine.renderMaxY) * engine.renderMaxZ);
			engine.brightnessBottomLeft = engine.mixAoBrightness(j1, i2, l1,
					k1, engine.renderMaxY * engine.renderMinZ,
					engine.renderMaxY * (1.0D - engine.renderMinZ),
					(1.0D - engine.renderMaxY) * (1.0D - engine.renderMinZ),
					(1.0D - engine.renderMaxY) * engine.renderMinZ);
			engine.brightnessBottomRight = engine.mixAoBrightness(j1, i2, l1,
					k1, engine.renderMinY * engine.renderMinZ,
					engine.renderMinY * (1.0D - engine.renderMinZ),
					(1.0D - engine.renderMinY) * (1.0D - engine.renderMinZ),
					(1.0D - engine.renderMinY) * engine.renderMinZ);
			engine.brightnessTopRight = engine.mixAoBrightness(j1, i2, l1, k1,
					engine.renderMinY * engine.renderMaxZ, engine.renderMinY
							* (1.0D - engine.renderMaxZ),
					(1.0D - engine.renderMinY) * (1.0D - engine.renderMaxZ),
					(1.0D - engine.renderMinY) * engine.renderMaxZ);

			if (flag1) {
				engine.colorRedTopLeft = engine.colorRedBottomLeft = engine.colorRedBottomRight = engine.colorRedTopRight = par5 * 0.6F;
				engine.colorGreenTopLeft = engine.colorGreenBottomLeft = engine.colorGreenBottomRight = engine.colorGreenTopRight = par6 * 0.6F;
				engine.colorBlueTopLeft = engine.colorBlueBottomLeft = engine.colorBlueBottomRight = engine.colorBlueTopRight = par7 * 0.6F;
			} else {
				engine.colorRedTopLeft = engine.colorRedBottomLeft = engine.colorRedBottomRight = engine.colorRedTopRight = 0.6F;
				engine.colorGreenTopLeft = engine.colorGreenBottomLeft = engine.colorGreenBottomRight = engine.colorGreenTopRight = 0.6F;
				engine.colorBlueTopLeft = engine.colorBlueBottomLeft = engine.colorBlueBottomRight = engine.colorBlueTopRight = 0.6F;
			}

			engine.colorRedTopLeft *= f3;
			engine.colorGreenTopLeft *= f3;
			engine.colorBlueTopLeft *= f3;
			engine.colorRedBottomLeft *= f4;
			engine.colorGreenBottomLeft *= f4;
			engine.colorBlueBottomLeft *= f4;
			engine.colorRedBottomRight *= f5;
			engine.colorGreenBottomRight *= f5;
			engine.colorBlueBottomRight *= f5;
			engine.colorRedTopRight *= f6;
			engine.colorGreenTopRight *= f6;
			engine.colorBlueTopRight *= f6;
			icon = engine.getBlockIcon(par1Block, engine.blockAccess, par2,
					par3, par4, 4);
			renderFaceXNeg(engine, par1Block, (double) par2, (double) par3,
					(double) par4, icon);

			if (RenderBlocks.fancyGrass
					&& icon.getIconName().equals("grass_side")
					&& !engine.hasOverrideBlockTexture()) {
				engine.colorRedTopLeft *= par5;
				engine.colorRedBottomLeft *= par5;
				engine.colorRedBottomRight *= par5;
				engine.colorRedTopRight *= par5;
				engine.colorGreenTopLeft *= par6;
				engine.colorGreenBottomLeft *= par6;
				engine.colorGreenBottomRight *= par6;
				engine.colorGreenTopRight *= par6;
				engine.colorBlueTopLeft *= par7;
				engine.colorBlueBottomLeft *= par7;
				engine.colorBlueBottomRight *= par7;
				engine.colorBlueTopRight *= par7;
				renderFaceXNeg(engine, par1Block, (double) par2, (double) par3,
						(double) par4, BlockGrass.getIconSideOverlay());
			}

			flag = true;
		}

		if (engine.renderAllFaces
				|| par1Block.shouldSideBeRendered(engine.blockAccess, par2 + 1,
						par3, par4, 5)) {
			if (engine.renderMaxX >= 1.0D) {
				++par2;
			}

			engine.aoLightValueScratchXYPN = par1Block
					.getAmbientOcclusionLightValue(engine.blockAccess, par2,
							par3 - 1, par4);
			engine.aoLightValueScratchXZPN = par1Block
					.getAmbientOcclusionLightValue(engine.blockAccess, par2,
							par3, par4 - 1);
			engine.aoLightValueScratchXZPP = par1Block
					.getAmbientOcclusionLightValue(engine.blockAccess, par2,
							par3, par4 + 1);
			engine.aoLightValueScratchXYPP = par1Block
					.getAmbientOcclusionLightValue(engine.blockAccess, par2,
							par3 + 1, par4);
			engine.aoBrightnessXYPN = par1Block.getMixedBrightnessForBlock(
					engine.blockAccess, par2, par3 - 1, par4);
			engine.aoBrightnessXZPN = par1Block.getMixedBrightnessForBlock(
					engine.blockAccess, par2, par3, par4 - 1);
			engine.aoBrightnessXZPP = par1Block.getMixedBrightnessForBlock(
					engine.blockAccess, par2, par3, par4 + 1);
			engine.aoBrightnessXYPP = par1Block.getMixedBrightnessForBlock(
					engine.blockAccess, par2, par3 + 1, par4);
			flag3 = Block.canBlockGrass[engine.blockAccess.getBlockId(par2 + 1,
					par3 + 1, par4)];
			flag2 = Block.canBlockGrass[engine.blockAccess.getBlockId(par2 + 1,
					par3 - 1, par4)];
			flag5 = Block.canBlockGrass[engine.blockAccess.getBlockId(par2 + 1,
					par3, par4 + 1)];
			flag4 = Block.canBlockGrass[engine.blockAccess.getBlockId(par2 + 1,
					par3, par4 - 1)];

			if (!flag2 && !flag4) {
				engine.aoLightValueScratchXYZPNN = engine.aoLightValueScratchXZPN;
				engine.aoBrightnessXYZPNN = engine.aoBrightnessXZPN;
			} else {
				engine.aoLightValueScratchXYZPNN = par1Block
						.getAmbientOcclusionLightValue(engine.blockAccess,
								par2, par3 - 1, par4 - 1);
				engine.aoBrightnessXYZPNN = par1Block
						.getMixedBrightnessForBlock(engine.blockAccess, par2,
								par3 - 1, par4 - 1);
			}

			if (!flag2 && !flag5) {
				engine.aoLightValueScratchXYZPNP = engine.aoLightValueScratchXZPP;
				engine.aoBrightnessXYZPNP = engine.aoBrightnessXZPP;
			} else {
				engine.aoLightValueScratchXYZPNP = par1Block
						.getAmbientOcclusionLightValue(engine.blockAccess,
								par2, par3 - 1, par4 + 1);
				engine.aoBrightnessXYZPNP = par1Block
						.getMixedBrightnessForBlock(engine.blockAccess, par2,
								par3 - 1, par4 + 1);
			}

			if (!flag3 && !flag4) {
				engine.aoLightValueScratchXYZPPN = engine.aoLightValueScratchXZPN;
				engine.aoBrightnessXYZPPN = engine.aoBrightnessXZPN;
			} else {
				engine.aoLightValueScratchXYZPPN = par1Block
						.getAmbientOcclusionLightValue(engine.blockAccess,
								par2, par3 + 1, par4 - 1);
				engine.aoBrightnessXYZPPN = par1Block
						.getMixedBrightnessForBlock(engine.blockAccess, par2,
								par3 + 1, par4 - 1);
			}

			if (!flag3 && !flag5) {
				engine.aoLightValueScratchXYZPPP = engine.aoLightValueScratchXZPP;
				engine.aoBrightnessXYZPPP = engine.aoBrightnessXZPP;
			} else {
				engine.aoLightValueScratchXYZPPP = par1Block
						.getAmbientOcclusionLightValue(engine.blockAccess,
								par2, par3 + 1, par4 + 1);
				engine.aoBrightnessXYZPPP = par1Block
						.getMixedBrightnessForBlock(engine.blockAccess, par2,
								par3 + 1, par4 + 1);
			}

			if (engine.renderMaxX >= 1.0D) {
				--par2;
			}

			i1 = l;

			if (engine.renderMaxX >= 1.0D
					|| !engine.blockAccess.isBlockOpaqueCube(par2 + 1, par3,
							par4)) {
				i1 = par1Block.getMixedBrightnessForBlock(engine.blockAccess,
						par2 + 1, par3, par4);
			}

			f7 = par1Block.getAmbientOcclusionLightValue(engine.blockAccess,
					par2 + 1, par3, par4);
			f9 = (engine.aoLightValueScratchXYPN
					+ engine.aoLightValueScratchXYZPNP + f7 + engine.aoLightValueScratchXZPP) / 4.0F;
			f8 = (engine.aoLightValueScratchXYZPNN
					+ engine.aoLightValueScratchXYPN
					+ engine.aoLightValueScratchXZPN + f7) / 4.0F;
			f11 = (engine.aoLightValueScratchXZPN + f7
					+ engine.aoLightValueScratchXYZPPN + engine.aoLightValueScratchXYPP) / 4.0F;
			f10 = (f7 + engine.aoLightValueScratchXZPP
					+ engine.aoLightValueScratchXYPP + engine.aoLightValueScratchXYZPPP) / 4.0F;
			f3 = (float) ((double) f9 * (1.0D - engine.renderMinY)
					* engine.renderMaxZ + (double) f8
					* (1.0D - engine.renderMinY) * (1.0D - engine.renderMaxZ)
					+ (double) f11 * engine.renderMinY
					* (1.0D - engine.renderMaxZ) + (double) f10
					* engine.renderMinY * engine.renderMaxZ);
			f4 = (float) ((double) f9 * (1.0D - engine.renderMinY)
					* engine.renderMinZ + (double) f8
					* (1.0D - engine.renderMinY) * (1.0D - engine.renderMinZ)
					+ (double) f11 * engine.renderMinY
					* (1.0D - engine.renderMinZ) + (double) f10
					* engine.renderMinY * engine.renderMinZ);
			f5 = (float) ((double) f9 * (1.0D - engine.renderMaxY)
					* engine.renderMinZ + (double) f8
					* (1.0D - engine.renderMaxY) * (1.0D - engine.renderMinZ)
					+ (double) f11 * engine.renderMaxY
					* (1.0D - engine.renderMinZ) + (double) f10
					* engine.renderMaxY * engine.renderMinZ);
			f6 = (float) ((double) f9 * (1.0D - engine.renderMaxY)
					* engine.renderMaxZ + (double) f8
					* (1.0D - engine.renderMaxY) * (1.0D - engine.renderMaxZ)
					+ (double) f11 * engine.renderMaxY
					* (1.0D - engine.renderMaxZ) + (double) f10
					* engine.renderMaxY * engine.renderMaxZ);
			k1 = engine.getAoBrightness(engine.aoBrightnessXYPN,
					engine.aoBrightnessXYZPNP, engine.aoBrightnessXZPP, i1);
			j1 = engine.getAoBrightness(engine.aoBrightnessXZPP,
					engine.aoBrightnessXYPP, engine.aoBrightnessXYZPPP, i1);
			i2 = engine.getAoBrightness(engine.aoBrightnessXZPN,
					engine.aoBrightnessXYZPPN, engine.aoBrightnessXYPP, i1);
			l1 = engine.getAoBrightness(engine.aoBrightnessXYZPNN,
					engine.aoBrightnessXYPN, engine.aoBrightnessXZPN, i1);
			engine.brightnessTopLeft = engine.mixAoBrightness(k1, l1, i2, j1,
					(1.0D - engine.renderMinY) * engine.renderMaxZ,
					(1.0D - engine.renderMinY) * (1.0D - engine.renderMaxZ),
					engine.renderMinY * (1.0D - engine.renderMaxZ),
					engine.renderMinY * engine.renderMaxZ);
			engine.brightnessBottomLeft = engine.mixAoBrightness(k1, l1, i2,
					j1, (1.0D - engine.renderMinY) * engine.renderMinZ,
					(1.0D - engine.renderMinY) * (1.0D - engine.renderMinZ),
					engine.renderMinY * (1.0D - engine.renderMinZ),
					engine.renderMinY * engine.renderMinZ);
			engine.brightnessBottomRight = engine.mixAoBrightness(k1, l1, i2,
					j1, (1.0D - engine.renderMaxY) * engine.renderMinZ,
					(1.0D - engine.renderMaxY) * (1.0D - engine.renderMinZ),
					engine.renderMaxY * (1.0D - engine.renderMinZ),
					engine.renderMaxY * engine.renderMinZ);
			engine.brightnessTopRight = engine.mixAoBrightness(k1, l1, i2, j1,
					(1.0D - engine.renderMaxY) * engine.renderMaxZ,
					(1.0D - engine.renderMaxY) * (1.0D - engine.renderMaxZ),
					engine.renderMaxY * (1.0D - engine.renderMaxZ),
					engine.renderMaxY * engine.renderMaxZ);

			if (flag1) {
				engine.colorRedTopLeft = engine.colorRedBottomLeft = engine.colorRedBottomRight = engine.colorRedTopRight = par5 * 0.6F;
				engine.colorGreenTopLeft = engine.colorGreenBottomLeft = engine.colorGreenBottomRight = engine.colorGreenTopRight = par6 * 0.6F;
				engine.colorBlueTopLeft = engine.colorBlueBottomLeft = engine.colorBlueBottomRight = engine.colorBlueTopRight = par7 * 0.6F;
			} else {
				engine.colorRedTopLeft = engine.colorRedBottomLeft = engine.colorRedBottomRight = engine.colorRedTopRight = 0.6F;
				engine.colorGreenTopLeft = engine.colorGreenBottomLeft = engine.colorGreenBottomRight = engine.colorGreenTopRight = 0.6F;
				engine.colorBlueTopLeft = engine.colorBlueBottomLeft = engine.colorBlueBottomRight = engine.colorBlueTopRight = 0.6F;
			}

			engine.colorRedTopLeft *= f3;
			engine.colorGreenTopLeft *= f3;
			engine.colorBlueTopLeft *= f3;
			engine.colorRedBottomLeft *= f4;
			engine.colorGreenBottomLeft *= f4;
			engine.colorBlueBottomLeft *= f4;
			engine.colorRedBottomRight *= f5;
			engine.colorGreenBottomRight *= f5;
			engine.colorBlueBottomRight *= f5;
			engine.colorRedTopRight *= f6;
			engine.colorGreenTopRight *= f6;
			engine.colorBlueTopRight *= f6;
			icon = engine.getBlockIcon(par1Block, engine.blockAccess, par2,
					par3, par4, 5);
			renderFaceXPos(engine, par1Block, (double) par2, (double) par3,
					(double) par4, icon);

			if (RenderBlocks.fancyGrass
					&& icon.getIconName().equals("grass_side")
					&& !engine.hasOverrideBlockTexture()) {
				engine.colorRedTopLeft *= par5;
				engine.colorRedBottomLeft *= par5;
				engine.colorRedBottomRight *= par5;
				engine.colorRedTopRight *= par5;
				engine.colorGreenTopLeft *= par6;
				engine.colorGreenBottomLeft *= par6;
				engine.colorGreenBottomRight *= par6;
				engine.colorGreenTopRight *= par6;
				engine.colorBlueTopLeft *= par7;
				engine.colorBlueBottomLeft *= par7;
				engine.colorBlueBottomRight *= par7;
				engine.colorBlueTopRight *= par7;
				renderFaceXPos(engine, par1Block, (double) par2, (double) par3,
						(double) par4, BlockGrass.getIconSideOverlay());
			}

			flag = true;
		}

		engine.enableAO = false;
		return flag;
	}

	public void renderFaceZPos(RenderBlocks engine, Block par1Block,
			double par2, double par4, double par6, Icon par8Icon) {
		Tessellator tessellator = Tessellator.instance;

		if (engine.hasOverrideBlockTexture()) {
			par8Icon = engine.overrideBlockTexture;
		}

		double d3 = (double) par8Icon
				.getInterpolatedU(16.0D - engine.renderMaxX * 16.0D);
		double d4 = (double) par8Icon
				.getInterpolatedU(16.0D - engine.renderMinX * 16.0D);
		double d5 = (double) par8Icon
				.getInterpolatedV(16.0D - engine.renderMaxY * 16.0D);
		double d6 = (double) par8Icon
				.getInterpolatedV(16.0D - engine.renderMinY * 16.0D);
		double d7;

		if (engine.renderMinX < 0.0D || engine.renderMaxX > 1.0D) {
			d3 = (double) par8Icon.getMinU();
			d4 = (double) par8Icon.getMaxU();
		}

		if (engine.renderMinY < 0.0D || engine.renderMaxY > 1.0D) {
			d5 = (double) par8Icon.getMinV();
			d6 = (double) par8Icon.getMaxV();
		}

		d7 = d4;
		double d8 = d3;
		double d9 = d5;
		double d10 = d6;

		if (engine.uvRotateWest == 1) {
			d3 = (double) par8Icon.getInterpolatedU(engine.renderMinY * 16.0D);
			d6 = (double) par8Icon
					.getInterpolatedV(16.0D - engine.renderMinX * 16.0D);
			d4 = (double) par8Icon.getInterpolatedU(engine.renderMaxY * 16.0D);
			d5 = (double) par8Icon
					.getInterpolatedV(16.0D - engine.renderMaxX * 16.0D);
			d9 = d5;
			d10 = d6;
			d7 = d3;
			d8 = d4;
			d5 = d6;
			d6 = d9;
		} else if (engine.uvRotateWest == 2) {
			d3 = (double) par8Icon
					.getInterpolatedU(16.0D - engine.renderMaxY * 16.0D);
			d5 = (double) par8Icon.getInterpolatedV(engine.renderMinX * 16.0D);
			d4 = (double) par8Icon
					.getInterpolatedU(16.0D - engine.renderMinY * 16.0D);
			d6 = (double) par8Icon.getInterpolatedV(engine.renderMaxX * 16.0D);
			d7 = d4;
			d8 = d3;
			d3 = d4;
			d4 = d8;
			d9 = d6;
			d10 = d5;
		} else if (engine.uvRotateWest == 3) {
			d3 = (double) par8Icon
					.getInterpolatedU(16.0D - engine.renderMinX * 16.0D);
			d4 = (double) par8Icon
					.getInterpolatedU(16.0D - engine.renderMaxX * 16.0D);
			d5 = (double) par8Icon.getInterpolatedV(engine.renderMaxY * 16.0D);
			d6 = (double) par8Icon.getInterpolatedV(engine.renderMinY * 16.0D);
			d7 = d4;
			d8 = d3;
			d9 = d5;
			d10 = d6;
		}

		double d11 = par2 + engine.renderMinX;
		double d12 = par2 + engine.renderMaxX;
		double d13 = par4 + engine.renderMinY;
		double d14 = par4 + engine.renderMaxY;
		double d15 = par6 + engine.renderMaxZ;

		if (engine.enableAO) {
			tessellator.setColorOpaque_F(engine.colorRedTopLeft,
					engine.colorGreenTopLeft, engine.colorBlueTopLeft);
			tessellator.setBrightness(engine.brightnessTopLeft);
			tessellator.addVertexWithUV(d11, d14, d15, d3, d5);
			tessellator.setColorOpaque_F(engine.colorRedBottomLeft,
					engine.colorGreenBottomLeft, engine.colorBlueBottomLeft);
			tessellator.setBrightness(engine.brightnessBottomLeft);
			tessellator.addVertexWithUV(d11, d13, d15, d8, d10);
			tessellator.setColorOpaque_F(engine.colorRedBottomRight,
					engine.colorGreenBottomRight, engine.colorBlueBottomRight);
			tessellator.setBrightness(engine.brightnessBottomRight);
			tessellator.addVertexWithUV(d12, d13, d15, d4, d6);
			tessellator.setColorOpaque_F(engine.colorRedTopRight,
					engine.colorGreenTopRight, engine.colorBlueTopRight);
			tessellator.setBrightness(engine.brightnessTopRight);
			tessellator.addVertexWithUV(d12, d14, d15, d7, d9);
		} else {
			tessellator.addVertexWithUV(d11, d14, d15, d3, d5);
			tessellator.addVertexWithUV(d11, d13, d15, d8, d10);
			tessellator.addVertexWithUV(d12, d13, d15, d4, d6);
			tessellator.addVertexWithUV(d12, d14, d15, d7, d9);
		}
	}

	public void renderFaceXPos(RenderBlocks engine, Block par1Block,
			double par2, double par4, double par6, Icon par8Icon) {
		Tessellator tessellator = Tessellator.instance;

		if (engine.hasOverrideBlockTexture()) {
			par8Icon = engine.overrideBlockTexture;
		}

		double d3 = (double) par8Icon
				.getInterpolatedU(16.0D - engine.renderMaxZ * 16.0D);
		double d4 = (double) par8Icon
				.getInterpolatedU(16.0D - engine.renderMinZ * 16.0D);
		double d5 = (double) par8Icon
				.getInterpolatedV(16.0D - engine.renderMinY * 16.0D);
		double d6 = (double) par8Icon
				.getInterpolatedV(16.0D - engine.renderMaxY * 16.0D);
		double d7;

		if (engine.renderMinZ < 0.0D || engine.renderMaxZ > 1.0D) {
			d3 = (double) par8Icon.getMinU();
			d4 = (double) par8Icon.getMaxU();
		}

		if (engine.renderMinY < 0.0D || engine.renderMaxY > 1.0D) {
			d5 = (double) par8Icon.getMinV();
			d6 = (double) par8Icon.getMaxV();
		}

		System.out.println(d6);

		d7 = d4;
		double d8 = d3;
		double d9 = d5;
		double d10 = d6;

		if (engine.uvRotateSouth == 2) {
			d3 = (double) par8Icon.getInterpolatedU(engine.renderMinY * 16.0D);
			d5 = (double) par8Icon
					.getInterpolatedV(16.0D - engine.renderMinZ * 16.0D);
			d4 = (double) par8Icon.getInterpolatedU(engine.renderMaxY * 16.0D);
			d6 = (double) par8Icon
					.getInterpolatedV(16.0D - engine.renderMaxZ * 16.0D);
			d9 = d5;
			d10 = d6;
			d7 = d3;
			d8 = d4;
			d5 = d6;
			d6 = d9;
		} else if (engine.uvRotateSouth == 1) {
			d3 = (double) par8Icon
					.getInterpolatedU(16.0D - engine.renderMaxY * 16.0D);
			d5 = (double) par8Icon.getInterpolatedV(engine.renderMaxZ * 16.0D);
			d4 = (double) par8Icon
					.getInterpolatedU(16.0D - engine.renderMinY * 16.0D);
			d6 = (double) par8Icon.getInterpolatedV(engine.renderMinZ * 16.0D);
			d7 = d4;
			d8 = d3;
			d3 = d4;
			d4 = d8;
			d9 = d6;
			d10 = d5;
		} else if (engine.uvRotateSouth == 3) {
			d3 = (double) par8Icon
					.getInterpolatedU(16.0D - engine.renderMinZ * 16.0D);
			d4 = (double) par8Icon
					.getInterpolatedU(16.0D - engine.renderMaxZ * 16.0D);
			d5 = (double) par8Icon.getInterpolatedV(engine.renderMaxY * 16.0D);
			d6 = (double) par8Icon.getInterpolatedV(engine.renderMinY * 16.0D);
			d7 = d4;
			d8 = d3;
			d9 = d5;
			d10 = d6;
		}

		double d11 = par2 + engine.renderMaxX;
		double d12 = par4 + engine.renderMinY;
		double d13 = par4 + engine.renderMaxY;
		double d14 = par6 + engine.renderMinZ;
		double d15 = par6 + engine.renderMaxZ;

		if (engine.enableAO) {
			tessellator.setColorOpaque_F(engine.colorRedTopLeft,
					engine.colorGreenTopLeft, engine.colorBlueTopLeft);
			tessellator.setBrightness(engine.brightnessTopLeft);
			tessellator.addVertexWithUV(d11, d12, d15, d8, d10);
			tessellator.setColorOpaque_F(engine.colorRedBottomLeft,
					engine.colorGreenBottomLeft, engine.colorBlueBottomLeft);
			tessellator.setBrightness(engine.brightnessBottomLeft);
			tessellator.addVertexWithUV(d11, d12, d14, d4, d6);
			tessellator.setColorOpaque_F(engine.colorRedBottomRight,
					engine.colorGreenBottomRight, engine.colorBlueBottomRight);
			tessellator.setBrightness(engine.brightnessBottomRight);
			tessellator.addVertexWithUV(d11, d13, d14, d7, d9);
			tessellator.setColorOpaque_F(engine.colorRedTopRight,
					engine.colorGreenTopRight, engine.colorBlueTopRight);
			tessellator.setBrightness(engine.brightnessTopRight);
			tessellator.addVertexWithUV(d11, d13, d15, d3, d5);
		} else {
			tessellator.addVertexWithUV(d11, d12, d15, d8, d10);
			tessellator.addVertexWithUV(d11, d12, d14, d4, d6);
			tessellator.addVertexWithUV(d11, d13, d14, d7, d9);
			tessellator.addVertexWithUV(d11, d13, d15, d3, d5);
		}
	}

	public void renderFaceXNeg(RenderBlocks engine, Block par1Block,
			double par2, double par4, double par6, Icon par8Icon) {
		Tessellator tessellator = Tessellator.instance;

		if (engine.hasOverrideBlockTexture()) {
			par8Icon = engine.overrideBlockTexture;
		}

		double d3 = (double) par8Icon
				.getInterpolatedU(engine.renderMinZ * 16.0D);
		double d4 = (double) par8Icon
				.getInterpolatedU(engine.renderMaxZ * 16.0D);
		double d5 = (double) par8Icon
				.getInterpolatedV(16.0D - engine.renderMaxY * 16.0D);
		double d6 = (double) par8Icon
				.getInterpolatedV(16.0D - engine.renderMinY * 16.0D);
		double d7;

		d7 = d5;
		d5 = d6;
		d6 = d7;

		if (engine.renderMinZ < 0.0D || engine.renderMaxZ > 1.0D) {
			d3 = (double) par8Icon.getMinU();
			d4 = (double) par8Icon.getMaxU();
		}

		if (engine.renderMinY < 0.0D || engine.renderMaxY > 1.0D) {
			d5 = (double) par8Icon.getMinV();
			d6 = (double) par8Icon.getMaxV();
		}

		d7 = d4;
		double d8 = d3;
		double d9 = d5;
		double d10 = d6;

		if (engine.uvRotateNorth == 1) {
			d3 = (double) par8Icon.getInterpolatedU(engine.renderMinY * 16.0D);
			d5 = (double) par8Icon
					.getInterpolatedV(16.0D - engine.renderMaxZ * 16.0D);
			d4 = (double) par8Icon.getInterpolatedU(engine.renderMaxY * 16.0D);
			d6 = (double) par8Icon
					.getInterpolatedV(16.0D - engine.renderMinZ * 16.0D);
			d9 = d5;
			d10 = d6;
			d7 = d3;
			d8 = d4;
			d5 = d6;
			d6 = d9;
		} else if (engine.uvRotateNorth == 2) {
			d3 = (double) par8Icon
					.getInterpolatedU(16.0D - engine.renderMaxY * 16.0D);
			d5 = (double) par8Icon.getInterpolatedV(engine.renderMinZ * 16.0D);
			d4 = (double) par8Icon
					.getInterpolatedU(16.0D - engine.renderMinY * 16.0D);
			d6 = (double) par8Icon.getInterpolatedV(engine.renderMaxZ * 16.0D);
			d7 = d4;
			d8 = d3;
			d3 = d4;
			d4 = d8;
			d9 = d6;
			d10 = d5;
		} else if (engine.uvRotateNorth == 3) {
			d3 = (double) par8Icon
					.getInterpolatedU(16.0D - engine.renderMinZ * 16.0D);
			d4 = (double) par8Icon
					.getInterpolatedU(16.0D - engine.renderMaxZ * 16.0D);
			d5 = (double) par8Icon.getInterpolatedV(engine.renderMaxY * 16.0D);
			d6 = (double) par8Icon.getInterpolatedV(engine.renderMinY * 16.0D);
			d7 = d4;
			d8 = d3;
			d9 = d5;
			d10 = d6;
		}

		double d11 = par2 + engine.renderMinX;
		double d12 = par4 + engine.renderMinY;
		double d13 = par4 + engine.renderMaxY;
		double d14 = par6 + engine.renderMinZ;
		double d15 = par6 + engine.renderMaxZ;

		if (engine.enableAO) {
			tessellator.setColorOpaque_F(engine.colorRedTopLeft,
					engine.colorGreenTopLeft, engine.colorBlueTopLeft);
			tessellator.setBrightness(engine.brightnessTopLeft);
			tessellator.addVertexWithUV(d11, d13, d15, d7, d9);
			tessellator.setColorOpaque_F(engine.colorRedBottomLeft,
					engine.colorGreenBottomLeft, engine.colorBlueBottomLeft);
			tessellator.setBrightness(engine.brightnessBottomLeft);
			tessellator.addVertexWithUV(d11, d13, d14, d3, d5);
			tessellator.setColorOpaque_F(engine.colorRedBottomRight,
					engine.colorGreenBottomRight, engine.colorBlueBottomRight);
			tessellator.setBrightness(engine.brightnessBottomRight);
			tessellator.addVertexWithUV(d11, d12, d14, d8, d10);
			tessellator.setColorOpaque_F(engine.colorRedTopRight,
					engine.colorGreenTopRight, engine.colorBlueTopRight);
			tessellator.setBrightness(engine.brightnessTopRight);
			tessellator.addVertexWithUV(d11, d12, d15, d4, d6);
		} else {
			tessellator.addVertexWithUV(d11, d13, d15, d7, d9);
			tessellator.addVertexWithUV(d11, d13, d14, d3, d5);
			tessellator.addVertexWithUV(d11, d12, d14, d8, d10);
			tessellator.addVertexWithUV(d11, d12, d15, d4, d6);
		}
	}
}
