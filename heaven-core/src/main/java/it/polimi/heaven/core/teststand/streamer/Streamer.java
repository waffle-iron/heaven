package it.polimi.heaven.core.teststand.streamer;

import it.polimi.heaven.core.teststand.EventProcessor;
import it.polimi.heaven.core.teststand.events.HeavenEvent;
import it.polimi.heaven.core.teststand.rspengine.events.Stimulus;

import java.io.FileReader;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

/**
 * 
 * General implementation of the Streamer used by the TestStand It process an
 * Experiment event trough the method process(Experiment e). It must be
 * initialized before processing to stop the processing until next
 * initialization. It builds an flow rate according to the provided
 * FlowRateProfiler and send events to the next provided EventProcessor in
 * CTEvent format
 * 
 * A limitation in the number of generated events can be set up in construction
 * 
 * 
 * 
 * @author Riccardo
 * 
 */
@Log4j
@Getter
@NoArgsConstructor
@AllArgsConstructor
public abstract class Streamer {
	@Setter
	protected int eventLimit;

	protected EventProcessor<Stimulus> engine;
	@Setter
	protected EventProcessor<HeavenEvent> collector;

	public Streamer(int eventLimit, EventProcessor<Stimulus> engine, ParsingTemplate parser) {
		this.eventLimit = eventLimit;
		this.engine = engine;
	}

	public Streamer(Encoder encoder, EventProcessor<Stimulus> engine, ParsingTemplate parser) {
		this.eventLimit = 1000;
		this.engine = engine;
	}

	public abstract boolean start(FileReader in);

	@SuppressWarnings("unchecked")
	public boolean setEngine(EventProcessor<?> ep) {
		try {
			this.engine = (EventProcessor<Stimulus>) ep;
			return true;
		} catch (ClassCastException cce) {
			log.error(cce.getMessage());
			return false;
		}
	}
}
