package com.epam.rd.payload.response;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class BookedCourseResponse {

    String courseTitle;
    String imageUrl;
    String email;

}
