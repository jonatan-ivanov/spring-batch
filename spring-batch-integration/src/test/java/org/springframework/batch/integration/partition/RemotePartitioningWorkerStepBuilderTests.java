/*
 * Copyright 2018-2022 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.batch.integration.partition;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.integration.channel.DirectChannel;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author Mahmoud Ben Hassine
 */
class RemotePartitioningWorkerStepBuilderTests {

	@Mock
	private Tasklet tasklet;

	@Test
	void inputChannelMustNotBeNull() {
		// given
		final RemotePartitioningWorkerStepBuilder builder = new RemotePartitioningWorkerStepBuilder("step");

		// when
		final Exception expectedException = assertThrows(IllegalArgumentException.class,
				() -> builder.inputChannel(null));

		// then
		assertThat(expectedException).hasMessage("inputChannel must not be null");
	}

	@Test
	void outputChannelMustNotBeNull() {
		// given
		final RemotePartitioningWorkerStepBuilder builder = new RemotePartitioningWorkerStepBuilder("step");

		// when
		final Exception expectedException = assertThrows(IllegalArgumentException.class,
				() -> builder.outputChannel(null));

		// then
		assertThat(expectedException).hasMessage("outputChannel must not be null");
	}

	@Test
	void jobExplorerMustNotBeNull() {
		// given
		final RemotePartitioningWorkerStepBuilder builder = new RemotePartitioningWorkerStepBuilder("step");

		// when
		final Exception expectedException = assertThrows(IllegalArgumentException.class,
				() -> builder.jobExplorer(null));

		// then
		assertThat(expectedException).hasMessage("jobExplorer must not be null");
	}

	@Test
	void stepLocatorMustNotBeNull() {
		// given
		final RemotePartitioningWorkerStepBuilder builder = new RemotePartitioningWorkerStepBuilder("step");

		// when
		final Exception expectedException = assertThrows(IllegalArgumentException.class,
				() -> builder.stepLocator(null));

		// then
		assertThat(expectedException).hasMessage("stepLocator must not be null");
	}

	@Test
	void beanFactoryMustNotBeNull() {
		// given
		final RemotePartitioningWorkerStepBuilder builder = new RemotePartitioningWorkerStepBuilder("step");

		// when
		final Exception expectedException = assertThrows(IllegalArgumentException.class,
				() -> builder.beanFactory(null));

		// then
		assertThat(expectedException).hasMessage("beanFactory must not be null");
	}

	@Test
	void testMandatoryInputChannel() {
		// given
		final RemotePartitioningWorkerStepBuilder builder = new RemotePartitioningWorkerStepBuilder("step");

		// when
		final Exception expectedException = assertThrows(IllegalArgumentException.class,
				() -> builder.tasklet(this.tasklet));

		// then
		assertThat(expectedException).hasMessage("An InputChannel must be provided");
	}

	@Test
	void testMandatoryJobExplorer() {
		// given
		DirectChannel inputChannel = new DirectChannel();
		final RemotePartitioningWorkerStepBuilder builder = new RemotePartitioningWorkerStepBuilder("step")
				.inputChannel(inputChannel);

		// when
		final Exception expectedException = assertThrows(IllegalArgumentException.class,
				() -> builder.tasklet(this.tasklet));

		// then
		assertThat(expectedException).hasMessage("A JobExplorer must be provided");
	}

}
