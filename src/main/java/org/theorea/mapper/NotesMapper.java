package org.theorea.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.theorea.model.Note;
import org.theorea.model.dto.NewNoteDto;
import org.theorea.model.dto.NoteDto;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface NotesMapper {

    @Mapping(target="createdAt", source = "createdAt")
    @Mapping(target="updatedAt", source = "updatedAt")
    NoteDto mapToNoteDto(Note note);

    Note mapToNote(NewNoteDto noteDto);
}
