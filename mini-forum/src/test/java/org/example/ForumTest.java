package org.example;

import org.example.enums.Status;
import org.example.model.Article;
import org.junit.Assert;
import org.junit.Test;

public class ForumTest {
    @Test
    public void checkApprovalOfArticle(){
        Article article = new Article(1, 1, "mesaj", Status.PENDING);
        article.approveArticle();

        Article expectedResult = new Article(1,1,"mesaj", Status.APPROVED);
        Assert.assertEquals(expectedResult, article);

        article.disapproveArticle();
        Assert.assertNotEquals(expectedResult, article);
    }

    @Test
    public void checkDisapprovalOfArticle(){
        Article article = new Article(1, 1, "mesaj", Status.APPROVED);
        article.disapproveArticle();

        Article expectedResult = new Article(1,1,"mesaj", Status.PENDING);
        Assert.assertEquals(expectedResult, article);

        article.approveArticle();
        Assert.assertNotEquals(expectedResult, article);

    }

}
