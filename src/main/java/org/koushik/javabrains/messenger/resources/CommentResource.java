package org.koushik.javabrains.messenger.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.koushik.javabrains.messenger.model.Comment;
import org.koushik.javabrains.messenger.service.CommentService;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class CommentResource {

	private CommentService commentService; 
	
	public CommentResource(String messageID) {
		super();
		this.commentService = new CommentService(messageID);
	}

	@GET
	public List<Comment> getAllComments() {
		return commentService.getAllComments();
	}
	
	@POST
	public Comment addComment(Comment comment) {
		return commentService.addComment(comment);
	}
	
	@PUT
	@Path("/{commentId}")
	public Comment updateComment(@PathParam("commentId") String id, Comment comment) {
		comment.setId(id);
		return commentService.updateComment(comment);
	}
	
	@DELETE
	@Path("/{commentId}")
	public void deleteComment(@PathParam("commentId") String commentId) {
		commentService.removeComment(commentId);
	}
	
	
	@GET
	@Path("/{commentId}")
	public Comment getMessage(@PathParam("commentId") long commentId) {
		//return commentService.getComment(messageId, commentId);
		return null;
	}
	/*@GET
	@Path("/{commentId}")
	public Comment getComment(@PathParam("messageId") String messageId, @PathParam("commentId") long commentId) {
		//System.out.println(comment.getPath());
		//System.out.println(comment.getMatrixParameters());
		return commentService.getComment(messageId, 1);
	}*/
}
