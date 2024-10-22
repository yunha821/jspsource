package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.BoardDTO;
import lombok.AllArgsConstructor;
import service.BoardService;
import service.BoardServiceImpl;

@AllArgsConstructor
public class BoardDeleteAction implements Action {
	
	private String path;
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		BoardDTO deleteDTO = new BoardDTO();
		
		deleteDTO.setBno(Integer.parseInt(request.getParameter("bno")));
		deleteDTO.setPassword(request.getParameter("password"));
		
		BoardService service = new BoardServiceImpl();	
		boolean updateFlag = service.delete(deleteDTO);

		
		if(updateFlag) {
			// 성공 시 bno 보내기(상세조회 시 필요하기 때문에)
			//path += "?bno=" + updateDTO.getBno();
		}else{
			path = "/modify.do?bno=" + deleteDTO.getBno();
		}
	
		return new ActionForward(path, true);
	}

}
