-- Print contents of `tbl`, with indentation.
-- `indent` sets the initial level of indentation.
function tprint (tbl, indent)
  if not indent then indent = 0 end
  for k, v in pairs(tbl) do
    formatting = string.rep("  ", indent) .. k .. ": "
    if type(v) == "table" then
      print(formatting)
      tprint(v, indent+1)
    elseif type(v) == 'boolean' then
      print(formatting .. tostring(v))		
    elseif type(v) == 'function' then
      print(formatting .. "function")
    else
      print(formatting .. v)
    end
  end
end

function onInRange(tbl)
	
	positionComponent = tbl.p1.components.PositionComponent
	--positionComponent.move(0, 0.001)
	positionComponent.rotate(0.0,0.0, 0.05)
	
	tprint(positionComponent, 1)
end
