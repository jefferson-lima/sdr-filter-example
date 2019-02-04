package lima.jefferson.sdrfilterexample;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.PersistentEntityResourceAssembler;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@RepositoryRestController
@RequiredArgsConstructor
public class CompanyCustomController {

  private final CompanyRepository repository;

  @GetMapping("/companies/filter")
  public ResponseEntity<?> filter(
      Company company,
      Pageable page,
      PagedResourcesAssembler assembler,
      PersistentEntityResourceAssembler entityAssembler
  ){

    ExampleMatcher matcher = ExampleMatcher.matching()
        .withIgnoreCase()
        .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

    Example example = Example.of(company, matcher);

    Page<?> result = this.repository.findAll(example, page);

    return ResponseEntity.ok(assembler.toResource(result, entityAssembler));

  }

}
