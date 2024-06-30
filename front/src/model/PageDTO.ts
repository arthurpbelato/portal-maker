export class PageDTO {
  page?: number

  size?: number


  static default(): PageDTO {
    let pageDetails: PageDTO = new PageDTO();
    pageDetails.page = 0;
    pageDetails.size = 20;

    return pageDetails;
  }
}
