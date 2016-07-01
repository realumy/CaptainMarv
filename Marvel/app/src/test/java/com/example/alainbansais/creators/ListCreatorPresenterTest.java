package com.example.alainbansais.creators;

import com.example.alainbansais.core.SyncExecutor;
import com.example.alainbansais.creators.ListCreatorRepository.RepositoryCreatorException;
import com.example.alainbansais.model.CreatorMarvel;
import com.squareup.otto.Bus;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricGradleTestRunner;

import java.util.Collections;
import java.util.List;

import static org.mockito.BDDMockito.*;

@RunWith(RobolectricGradleTestRunner.class)
public class ListCreatorPresenterTest {
    @Mock private ListCreatorView view;
    @Mock private ListCreatorRepository repository;
    private ListCreatorPresenter presenter;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        presenter = new ListCreatorPresenter(view, repository, new SyncExecutor(), new Bus());
        presenter.register();
    }

    @Test
    public void load_WhenRepositoryFailed_ShouldDisplayError() throws RepositoryCreatorException {
        //Given
        //noinspection unchecked
        given(repository.get()).willThrow(RepositoryCreatorException.class);

        //When
        presenter.load();

        //Then
        verify(view).displayError();
    }

    @Test
    public void load_WhenRepositorySucceed_ButLisIsEmpty_ShouldDisplayEmptyResult() throws RepositoryCreatorException {
        // Given
        final List<CreatorMarvel> list = Collections.emptyList();
        given(repository.get()).willReturn(list);

        // When
        presenter.load();

        // Then
        verify(view).displayEmptyResult();
    }

    @Test
    public void load_WhenRepositorySucceed_AndLisIsNotEmpty_ShouldDisplayResult() throws RepositoryCreatorException {
        // Given
        final CreatorMarvel creatorMarvel = mock(CreatorMarvel.class);
        final List<CreatorMarvel> list = Collections.singletonList(creatorMarvel);
        given(repository.get()).willReturn(list);

        // When
        presenter.load();

        // Then
        verify(view).displayCreatorList(list);
    }
}