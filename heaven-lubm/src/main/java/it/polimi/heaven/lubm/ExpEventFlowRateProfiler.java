package it.polimi.heaven.lubm;

import it.polimi.heaven.core.teststand.streamer.ParsingTemplate;

public class ExpEventFlowRateProfiler extends LUBMFlowRateProfiler {

	private int power = 0;

	public ExpEventFlowRateProfiler(ParsingTemplate parser, int base, int initSize, int experiment, int timing) {
		super(FlowRateProfile.EXP, parser, base, 1, initSize, experiment, timing);
		current_heaven_input = initSize;
	}

	@Override
	public void updateSize() {
		power++;
		roundSize = (int) Math.pow(x, power);
	}
}
