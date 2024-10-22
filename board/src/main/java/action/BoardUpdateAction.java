package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.BoardDTO;
import lombok.AllArgsConstructor;
import service.BoardService;
import service.BoardServiceImpl;

@AllArgsConstructor
public class BoardUpdateAction implements Action {
	
	private String path;
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		BoardDTO updateDTO = new BoardDTO();
		
		updateDTO.setTitle(request.getParameter("title"));
		updateDTO.setContent(request.getParameter("content"));
		updateDTO.setBno(Integer.parseInt(request.getParameter("bno")));
		updateDTO.setPassword(request.getParameter("password"));
		
		BoardService service = new BoardServiceImpl();	
		boolean updateFlag = service.update(updateDTO);

		
		if(updateFlag) {
			// 성공 시 bno 보내기(상세조회 시 필요하기 때문에)
			path += "?bno=" + updateDTO.getBno();
		}else{
			path += "/modify.do?bno=" + updateDTO.getBno();
		}
	
		return new ActionForward(path, true);
	}

}
