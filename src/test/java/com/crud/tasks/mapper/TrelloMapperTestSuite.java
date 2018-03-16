package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;

@RunWith(MockitoJUnitRunner.class)
public class TrelloMapperTestSuite {

    @InjectMocks
    private TrelloMapper trelloMapper;

    @Test
    public void testMapToBoards() {
        //Given
        List<TrelloListDto> xxListOfTrelloListsDto = new ArrayList<>();
        xxListOfTrelloListsDto.add(new TrelloListDto("xx1", "testList1", true));
        xxListOfTrelloListsDto.add(new TrelloListDto("xx2", "testList2", true));
        xxListOfTrelloListsDto.add(new TrelloListDto("xx3", "testList3", false));
        TrelloBoardDto trelloBoard1Dto = new TrelloBoardDto("xx", "testBoard1", xxListOfTrelloListsDto);

        List<TrelloListDto> zzListOfTrelloListsDto = new ArrayList<>();
        zzListOfTrelloListsDto.add(new TrelloListDto("zz1", "testList1", true));
        zzListOfTrelloListsDto.add(new TrelloListDto("zz2", "testList2", false));
        zzListOfTrelloListsDto.add(new TrelloListDto("zz3", "testList3", true));
        TrelloBoardDto trelloBoard2Dto = new TrelloBoardDto("zz", "testBoard2", zzListOfTrelloListsDto);

        List<TrelloBoardDto> listOfTrelloBoardsDto = new ArrayList<>();
        listOfTrelloBoardsDto.add(trelloBoard1Dto);
        listOfTrelloBoardsDto.add(trelloBoard2Dto);

        //When
        List<TrelloBoard> listOfTrelloBoards = trelloMapper.mapToBoards(listOfTrelloBoardsDto);

        //Then
        Assert.assertEquals(2, listOfTrelloBoards.size());
        Assert.assertEquals(3, listOfTrelloBoards.get(0).getLists().size());
        Assert.assertEquals(3, listOfTrelloBoards.get(1).getLists().size());
        Assert.assertFalse(listOfTrelloBoards.get(0).getLists().get(2).isClosed());
        Assert.assertFalse(listOfTrelloBoards.get(1).getLists().get(1).isClosed());
        Assert.assertTrue(listOfTrelloBoards.get(1).getLists().get(2).isClosed());
        Assert.assertEquals("xx", listOfTrelloBoards.get(0).getId());
        Assert.assertEquals("zz", listOfTrelloBoards.get(1).getId());
        Assert.assertEquals("testList2", listOfTrelloBoards.get(1).getLists().get(1).getName());
    }

    @Test
    public void testMapToBoardsDto() {
        //Given
        List<TrelloList> xxListOfTrelloLists = new ArrayList<>();
        xxListOfTrelloLists.add(new TrelloList("xx1", "testList1", false));
        xxListOfTrelloLists.add(new TrelloList("xx2", "testList2", true));
        xxListOfTrelloLists.add(new TrelloList("xx3", "testList3", false));
        TrelloBoard trelloBoard1 = new TrelloBoard("xx", "testBoard1", xxListOfTrelloLists);

        List<TrelloList> zzListOfTrelloLists = new ArrayList<>();
        zzListOfTrelloLists.add(new TrelloList("zz1", "testList1", true));
        zzListOfTrelloLists.add(new TrelloList("zz2", "testList2", false));
        zzListOfTrelloLists.add(new TrelloList("zz3", "testList3", false));
        TrelloBoard trelloBoard2 = new TrelloBoard("zz", "testBoard2", zzListOfTrelloLists);

        List<TrelloBoard> listOfTrelloBoards = new ArrayList<>();
        listOfTrelloBoards.add(trelloBoard1);
        listOfTrelloBoards.add(trelloBoard2);

        //When
        List<TrelloBoardDto> listOfTrelloBoardsDto = trelloMapper.mapToBoardsDto(listOfTrelloBoards);

        //Then
        Assert.assertEquals(2, listOfTrelloBoardsDto.size());
        Assert.assertEquals(3, listOfTrelloBoardsDto.get(0).getLists().size());
        Assert.assertEquals(3, listOfTrelloBoardsDto.get(1).getLists().size());
        Assert.assertFalse(listOfTrelloBoardsDto.get(0).getLists().get(0).isClosed());
        Assert.assertFalse(listOfTrelloBoardsDto.get(1).getLists().get(2).isClosed());
        Assert.assertTrue(listOfTrelloBoardsDto.get(1).getLists().get(0).isClosed());
    }

    @Test
    public void testMapToList() {
        //Given
        List<TrelloListDto> listOfTrelloListsDto = new ArrayList<>();
        listOfTrelloListsDto.add(new TrelloListDto("xx1", "testList1", true));
        listOfTrelloListsDto.add(new TrelloListDto("xx2", "testList2", true));
        listOfTrelloListsDto.add(new TrelloListDto("xx3", "testList3", false));

        //When
        List<TrelloList> listOfTrelloLists = trelloMapper.mapToList(listOfTrelloListsDto);

        //Then
        Assert.assertEquals(3, listOfTrelloLists.size());
        Assert.assertEquals("xx1", listOfTrelloLists.get(0).getId());
        Assert.assertEquals("testList2", listOfTrelloLists.get(1).getName());
        Assert.assertFalse(listOfTrelloLists.get(2).isClosed());
    }

    @Test
    public void testMapToListDto() {
        //Given
        List<TrelloList> listOfTrelloLists = new ArrayList<>();
        listOfTrelloLists.add(new TrelloList("xx1", "testList1", false));
        listOfTrelloLists.add(new TrelloList("xx2", "testList2", true));

        //When
        List<TrelloListDto> listOfTrelloListsDto = trelloMapper.mapToListDto(listOfTrelloLists);

        //Then
        Assert.assertEquals(2, listOfTrelloListsDto.size());
        Assert.assertEquals("xx2", listOfTrelloListsDto.get(1).getId());
        Assert.assertEquals("testList1", listOfTrelloListsDto.get(0).getName());
        Assert.assertTrue(listOfTrelloListsDto.get(1).isClosed());
    }

    @Test
    public void testMapToCardDto() {
        //Given
        TrelloCard trelloCard = new TrelloCard("testCard", "testing", "bottom", "4236522x564");

        //When
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);

        //Then
        Assert.assertEquals("testCard", trelloCardDto.getName());
        Assert.assertEquals("testing", trelloCardDto.getDescription());
        Assert.assertEquals("bottom", trelloCardDto.getPos());
        Assert.assertEquals("4236522x564", trelloCardDto.getListId());
    }

    @Test
    public void testMapToCard() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("testCard", "testing", "bottom", "4236522x564");

        //When
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);

        //Then
        Assert.assertEquals("testCard", trelloCard.getName());
        Assert.assertEquals("testing", trelloCard.getDescription());
        Assert.assertEquals("bottom", trelloCard.getPos());
        Assert.assertEquals("4236522x564", trelloCard.getListId());
    }
}